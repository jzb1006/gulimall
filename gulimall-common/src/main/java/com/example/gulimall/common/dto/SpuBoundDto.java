package com.example.gulimall.common.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhibinJiang on 2022/6/27
 */
@Data
public class SpuBoundDto {
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
