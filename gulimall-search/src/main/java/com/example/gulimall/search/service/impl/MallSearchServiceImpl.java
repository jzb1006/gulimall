package com.example.gulimall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import com.example.gulimall.search.constant.EsConstant;
import com.example.gulimall.search.service.IMallSearchService;
import com.example.gulimall.search.vo.BankVo;
import com.example.gulimall.search.vo.SearchParam;
import com.example.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MallSearchServiceImpl implements IMallSearchService {

    @Autowired
    ElasticsearchClient client;

    @Override
    public SearchResult search(SearchParam searchParam) {

        BoolQuery query = buildSearchRequest(searchParam);
        SearchResponse<SearchResult> search = null;
        try {
            search = client.search(s -> s
                            .index(EsConstant.PRODUCT_INDEX)
                            .query(q -> q.bool(query)),
                    SearchResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TotalHits total = search.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;
        return search.hits().hits().get(0).source();
    }

    private BoolQuery buildSearchRequest(SearchParam searchParam) {
        BoolQuery boolQuery = null;

        if (searchParam.getKeyword() != null) {
            MatchQuery matchQuery = new MatchQuery.Builder().field("skuTitle").query(searchParam.getKeyword()).build();
            boolQuery = BoolQuery.of(
                    b -> b.must((List<Query>) matchQuery)
            );
        }

        if (searchParam.getCatalogId() != null) {
            TermQuery termQuery = new TermQuery.Builder().field("catalogId").value(searchParam.getCatalogId()).build();
            boolQuery = BoolQuery.of(
                    b -> b.filter((List<Query>) termQuery)
            );
        }


        return boolQuery;
    }
}

