package com.example.gulimall.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhibinJiang on 2022/6/28
 */
@Configuration
public class GulimallElasticSearchConfig {


    @Bean
    public ElasticsearchClient esClient() {
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost("1.117.160.6", 9200)).build();

// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

// And create the API client

        return new ElasticsearchClient(transport);
    }
}
