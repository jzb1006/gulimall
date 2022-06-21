package com.example.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * sku销售属性&值
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity {
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
     * attr_id
     */
	private Long attrId;
    /**
     * 销售属性名
     */
	private String attrName;
    /**
     * 销售属性值
     */
	private String attrValue;
    /**
     * 顺序
     */
	private Integer attrSort;
}