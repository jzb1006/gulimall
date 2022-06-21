package com.example.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 专题商品
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_home_subject_spu")
public class HomeSubjectSpuEntity {
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * 专题名字
     */
	private String name;
    /**
     * 专题id
     */
	private Long subjectId;
    /**
     * spu_id
     */
	private Long spuId;
    /**
     * 排序
     */
	private Integer sort;
}