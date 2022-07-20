package com.example.gulimall.ware.service;

import com.example.gulimall.common.service.CrudService;
import com.example.gulimall.ware.dto.WareSkuDTO;
import com.example.gulimall.ware.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
public interface WareSkuService extends CrudService<WareSkuEntity, WareSkuDTO> {
    Long lockWareSku(@Param("skuId") Long skuId, @Param("num") Integer num, @Param("wareId") Long wareId);

    Long unlockWareSku(@Param("skuId") Long skuId, @Param("num") Integer num, @Param("wareId") Long wareId);

}