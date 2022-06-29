package com.example.gulimall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.example.gulimall.common.dto.es.SkuModel;
import com.example.gulimall.search.constant.EsConstant;
import com.example.gulimall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author ZhibinJiang on 2022/6/29
 */
@Service
@Slf4j
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    ElasticsearchClient client;

    @Override
    public Boolean save(List<SkuModel> skuModelList) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (SkuModel skuModel : skuModelList) {
            br.operations(op -> op.index(idx -> idx
                    .index(EsConstant.PRODUCT_INDEX)
                    .document(skuModel)
            ));
        }
        BulkResponse result = client.bulk(br.build());
        // Log errors, if any
        if (result.errors()) {
            log.error("Bulk had errors");
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    log.error(item.error().reason());
                }
            }
            return false;
        }
        return true;
    }
}
