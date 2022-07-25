package com.example.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.gulimall.common.dto.mq.OrderTo;
import com.example.gulimall.common.dto.mq.SeckillOrderTo;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.order.dao.OrderDao;
import com.example.gulimall.order.dto.OrderDTO;
import com.example.gulimall.order.entity.OrderEntity;
import com.example.gulimall.order.feign.WareFeignService;
import com.example.gulimall.order.service.OrderItemService;
import com.example.gulimall.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
@Slf4j
public class OrderServiceImpl extends CrudServiceImpl<OrderDao, OrderEntity, OrderDTO> implements OrderService {

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    WareFeignService wareFeignService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public QueryWrapper<OrderEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    @Transactional
    public Boolean submitOrder() {
        // todo 创建主订单
        OrderEntity order = new OrderEntity();
        order.setOrderSn(IdWorker.getTimeId());
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setStatus(0);
        baseDao.insert(order);
        // todo 子订单创建
        orderItemService.insertOrderItem(order.getId(), order.getOrderSn());

        // todo 远程扣减库存
        wareFeignService.lockWareSku(1L, 2, 1L);

        // todo 发送消息到MQ
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", order);
        return true;
    }

    @Override
    public void closeOrder(OrderEntity orderEntity) {
        orderEntity.setStatus(4); // 关闭订单
        HashMap<String, Object> map = new HashMap<>();
        map.put("order_sn", orderEntity.getOrderSn());
        baseDao.update(orderEntity, getWrapper(map));

        // todo 关单后发送消息通知其他服务进行关单相关的操作，如解锁库存
        OrderTo orderTo = new OrderTo();
        BeanUtils.copyProperties(orderEntity, orderTo);
        rabbitTemplate.convertAndSend("order-event-exchange", "order.release.other", orderTo);

    }

    @Transactional
    @Override
    public void createSeckillOrder(SeckillOrderTo orderTo) {
        // todo 模拟创建秒杀订单
        log.info("创建秒杀订单：{}", orderTo);
//        MemberResponseVo memberResponseVo = LoginInterceptor.loginUser.get();
//        //1. 创建订单
//        OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setOrderSn(orderTo.getOrderSn());
//        orderEntity.setMemberId(orderTo.getMemberId());
//        if (memberResponseVo!=null){
//            orderEntity.setMemberUsername(memberResponseVo.getUsername());
//        }
//        orderEntity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
//        orderEntity.setCreateTime(new Date());
//        orderEntity.setPayAmount(orderTo.getSeckillPrice().multiply(new BigDecimal(orderTo.getNum())));
//        this.save(orderEntity);
//        //2. 创建订单项
//        R r = productFeignService.info(orderTo.getSkuId());
//        if (r.getCode() == 0) {
//            SeckillSkuInfoVo skuInfo = r.getData("skuInfo", new TypeReference<SeckillSkuInfoVo>() {
//            });
//            OrderItemEntity orderItemEntity = new OrderItemEntity();
//            orderItemEntity.setOrderSn(orderTo.getOrderSn());
//            orderItemEntity.setSpuId(skuInfo.getSpuId());
//            orderItemEntity.setCategoryId(skuInfo.getCatalogId());
//            orderItemEntity.setSkuId(skuInfo.getSkuId());
//            orderItemEntity.setSkuName(skuInfo.getSkuName());
//            orderItemEntity.setSkuPic(skuInfo.getSkuDefaultImg());
//            orderItemEntity.setSkuPrice(skuInfo.getPrice());
//            orderItemEntity.setSkuQuantity(orderTo.getNum());
//            orderItemService.save(orderItemEntity);
//        }
    }


}