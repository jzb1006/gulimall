package com.example.gulimall.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import com.example.gulimall.search.vo.BankVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;


/**
 * @author ZhibinJiang on 2022/6/28
 */
@SpringBootTest
@Slf4j
class GulimallElasticSearchConfigTest {

    @Autowired
    ElasticsearchClient client;

    @Test
    void esClient() throws IOException {
        MatchQuery query = new MatchQuery.Builder().field("address").query("Lane").build();
        SearchResponse<BankVo> search = client.search(s -> s
                        .index("bank")
                        .query(q -> q.match(query)),
                BankVo.class);
        TotalHits total = search.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            log.info("There are " + total.value() + " results");
        } else {
            log.info("There are more than " + total.value() + " results");
        }

        List<Hit<BankVo>> hits = search.hits().hits();
        for (Hit<BankVo> hit: hits) {
            BankVo product = hit.source();
            log.info(product.toString());
            log.info("Found address " + product.getAddress() + ", score " + hit.score());
        }
    }
}