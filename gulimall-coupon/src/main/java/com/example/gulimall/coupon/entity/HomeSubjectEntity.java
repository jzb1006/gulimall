package com.example.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_home_subject")
public class HomeSubjectEntity {
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
     * 专题标题
     */
	private String title;
    /**
     * 专题副标题
     */
	private String subTitle;
    /**
     * 显示状态
     */
	private Integer status;
    /**
     * 详情连接
     */
	private String url;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 专题图片地址
     */
	private String img;
}