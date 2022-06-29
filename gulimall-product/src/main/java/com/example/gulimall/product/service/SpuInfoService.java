package com.example.gulimall.product.service;

import com.example.gulimall.common.service.CrudService;
import com.example.gulimall.product.dto.SpuInfoDTO;
import com.example.gulimall.product.entity.SpuInfoEntity;

/**
 * spu信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
public interface SpuInfoService extends CrudService<SpuInfoEntity, SpuInfoDTO> {

    void up(Long spuId);
}