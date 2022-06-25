package com.example.gulimall.product.dto;

import com.example.gulimall.common.valid.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


/**
 * 品牌
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Data
@ApiModel(value = "品牌")
public class BrandDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Null(message = "新增不能设置ID",groups = AddGroup.class)
	@ApiModelProperty(value = "品牌id")
	private Long brandId;

	@NotNull(message = "品牌不能为空",groups = AddGroup.class)
	@ApiModelProperty(value = "品牌名")
	private String name;

	@URL(message = "必须是URL")
	@ApiModelProperty(value = "品牌logo地址")
	private String logo;

	@ApiModelProperty(value = "介绍")
	private String descript;

	@ApiModelProperty(value = "显示状态[0-不显示；1-显示]")
	private Integer showStatus;

	@Pattern(regexp = "^[a-z]$",message = "必须是a-z")
	@ApiModelProperty(value = "检索首字母")
	private String firstLetter;


	@ApiModelProperty(value = "排序")
	private Integer sort;


}