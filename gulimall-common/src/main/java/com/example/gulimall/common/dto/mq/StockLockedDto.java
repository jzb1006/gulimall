package com.example.gulimall.common.dto.mq;

import lombok.Data;

import java.util.List;

@Data
public class StockLockedDto {
    private Long id; // 库存工作单ID
    private StockDetailTo detailTo;
}
