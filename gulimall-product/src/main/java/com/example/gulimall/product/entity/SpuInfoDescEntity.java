package com.example.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * spu信息介绍
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
	private Long spuId;
    /**
     * 商品介绍
     */
	private String decript;
}