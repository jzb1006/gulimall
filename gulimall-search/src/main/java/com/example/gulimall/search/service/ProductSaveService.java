package com.example.gulimall.search.service;

import com.example.gulimall.common.dto.es.SkuModel;

import java.io.IOException;
import java.util.List;

/**
 * @author ZhibinJiang on 2022/6/29
 */
public interface ProductSaveService {
    Boolean save(List<SkuModel> skuModelList) throws IOException;
}
