package com.example.gulimall.common.dto.es;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品spu积分设置
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@ApiModel(value = "商品spu积分设置")
public class SpuBoundsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
//
//	@ApiModelProperty(value = "id")
//	private Long id;
//
//	@ApiModelProperty(value = "")
//	private Long spuId;
//
//	@ApiModelProperty(value = "成长积分")
//	private BigDecimal growBounds;
//
//	@ApiModelProperty(value = "购物积分")
//	private BigDecimal buyBounds;
//
//	@ApiModelProperty(value = "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]")
//	private Integer work;

	private Long skuId;
	@ApiModelProperty(value = "")
    private Long spuId;
	private String skuTitle;
	private BigDecimal skuPrice;
	private String skuImg;
	private Boolean saleCount;
	private Long hasStock;
	private Long hotScore;
	private Long brandId;
	private Long catalogId;
	private String brandName;
	private String brandImg;
	private String catalogName;
//	private List<SkuModel.Attr> attrs;
//
//	@Data
//	public static class Attr implements Serializable {
//		private static final long serialVersionUID = 1L;
//		private Long attrId;
//		private Long attrName;
//		private Long attrValue;
//	}


}