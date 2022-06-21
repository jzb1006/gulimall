package com.example.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 积分变化历史记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("ums_integration_change_history")
public class IntegrationChangeHistoryEntity {
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
     * create_time
     */
	private Date createTime;
    /**
     * 变化的值
     */
	private Integer changeCount;
    /**
     * 备注
     */
	private String note;
    /**
     * 来源[0->购物；1->管理员修改;2->活动]
     */
	private Integer sourceTyoe;
}