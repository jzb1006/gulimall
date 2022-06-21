package com.example.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.gulimall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 库存工作单
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wms_ware_order_task_detail")
public class WareOrderTaskDetailEntity {
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
     * sku_name
     */
	private String skuName;
    /**
     * 购买个数
     */
	private Integer skuNum;
    /**
     * 工作单id
     */
	private Long taskId;
}