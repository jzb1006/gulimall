package com.example.gulimall.search.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchParam {
    /**
     * 关键字搜索
     */
    private String keywords;

    /**
     * 分类Id
     */
    private Long catalogId;

    /**
     * 排序
     * sort = saleCount,skuPrice,skuScore
     */
    private String sort;

    /**
     * hasStock（是否有货）、skuPrice区间、brandId、cataLog3Id、attrs
     * hasStock=0/1
     * skuPrice=1_500/_500\500_
     * brandId=1,
     * attrs=2_5寸:6寸
     */
    private Integer hasStock; //是否显示有货
    private String skuPrice;
    private List<Long> brandId;
    private List<Long> attrs;
    private Integer pageNum;
}
