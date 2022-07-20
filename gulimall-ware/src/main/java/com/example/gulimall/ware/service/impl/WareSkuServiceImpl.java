package com.example.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.gulimall.common.dto.mq.StockDetailTo;
import com.example.gulimall.common.dto.mq.StockLockedDto;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.ware.dao.WareSkuDao;
import com.example.gulimall.ware.dto.WareOrderTaskDTO;
import com.example.gulimall.ware.dto.WareOrderTaskDetailDTO;
import com.example.gulimall.ware.dto.WareSkuDTO;
import com.example.gulimall.ware.entity.WareSkuEntity;
import com.example.gulimall.ware.service.WareOrderTaskDetailService;
import com.example.gulimall.ware.service.WareOrderTaskService;
import com.example.gulimall.ware.service.WareSkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品库存
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class WareSkuServiceImpl extends CrudServiceImpl<WareSkuDao, WareSkuEntity, WareSkuDTO> implements WareSkuService {

    @Autowired
    WareOrderTaskService wareOrderTaskService;

    @Autowired
    WareOrderTaskDetailService wareOrderTaskDetailService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public QueryWrapper<WareSkuEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public Long lockWareSku(Long skuId, Integer num, Long wareId) {
        // 保存库存工作单的详情
        WareOrderTaskDTO taskDTO = new WareOrderTaskDTO();
        taskDTO.setOrderSn(IdWorker.getTimeId());
        wareOrderTaskService.save(taskDTO);


        Long aLong = baseDao.lockWareSku(skuId, num, wareId);
        if (aLong > 0) {
            // 保存库存工作单的详情
            WareOrderTaskDetailDTO detailDTO = new WareOrderTaskDetailDTO();
            detailDTO.setSkuId(skuId);
            detailDTO.setSkuNum(num);
            detailDTO.setTaskId(taskDTO.getId());
            detailDTO.setWareId(wareId);
            detailDTO.setLockStatus(1);
            wareOrderTaskDetailService.save(detailDTO);

            StockLockedDto stockLockedDto = new StockLockedDto();
            StockDetailTo stockDetailTo = new StockDetailTo();
            BeanUtils.copyProperties(detailDTO, stockDetailTo);
            stockLockedDto.setId(taskDTO.getId());
            stockLockedDto.setDetailTo(stockDetailTo);
            rabbitTemplate.convertAndSend("stock-event-exchange", "stock.locked", stockLockedDto);
        }
        return aLong;
    }

    @Override
    public Long unlockWareSku(Long skuId, Integer num, Long wareId) {
        return baseDao.unlockStock(skuId, num, wareId);
    }
}