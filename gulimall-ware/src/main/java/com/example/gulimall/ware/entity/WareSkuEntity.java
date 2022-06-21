package com.example.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品库存
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wms_ware_sku")
public class WareSkuEntity {
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * sku_id
     */
	private Long skuId;
    /**
     * 仓库id
     */
	private Long wareId;
    /**
     * 库存数
     */
	private Integer stock;
    /**
     * sku_name
     */
	private String skuName;
    /**
     * 锁定库存
     */
	private Integer stockLocked;
}