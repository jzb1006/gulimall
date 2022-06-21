package com.example.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 会员登录记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("ums_member_login_log")
public class MemberLoginLogEntity {
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * member_id
     */
	private Long memberId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * ip
     */
	private String ip;
    /**
     * city
     */
	private String city;
    /**
     * 登录类型[1-web，2-app]
     */
	private Integer loginType;
}