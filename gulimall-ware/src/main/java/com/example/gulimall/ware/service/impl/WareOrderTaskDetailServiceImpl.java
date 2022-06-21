package com.example.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.ware.dao.WareOrderTaskDetailDao;
import com.example.gulimall.ware.dto.WareOrderTaskDetailDTO;
import com.example.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.example.gulimall.ware.service.WareOrderTaskDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class WareOrderTaskDetailServiceImpl extends CrudServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity, WareOrderTaskDetailDTO> implements WareOrderTaskDetailService {

    @Override
    public QueryWrapper<WareOrderTaskDetailEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WareOrderTaskDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}