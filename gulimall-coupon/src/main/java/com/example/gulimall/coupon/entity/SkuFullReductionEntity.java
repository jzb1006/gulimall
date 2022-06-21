package com.example.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品满减信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_sku_full_reduction")
public class SkuFullReductionEntity {
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * spu_id
     */
	private Long skuId;
    /**
     * 满多少
     */
	private BigDecimal fullPrice;
    /**
     * 减多少
     */
	private BigDecimal reducePrice;
    /**
     * 是否参与其他优惠
     */
	private Integer addOther;
}