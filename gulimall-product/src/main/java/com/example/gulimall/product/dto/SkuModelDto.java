package com.example.gulimall.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ZhibinJiang on 2022/6/29
 */
@Data
public class SkuModelDto {
    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Boolean saleCount;
    private Long hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<Attr> attrs;

    @Data
    public static class Attr{
        private Long attrId;
        private Long attrName;
        private Long attrValue;
    }
}
