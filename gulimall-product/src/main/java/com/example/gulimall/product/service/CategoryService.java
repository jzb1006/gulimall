package com.example.gulimall.product.service;

import com.example.gulimall.common.service.CrudService;
import com.example.gulimall.product.dto.CategoryDTO;
import com.example.gulimall.product.entity.CategoryEntity;

import java.util.List;

/**
 * 商品三级分类
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
public interface CategoryService extends CrudService<CategoryEntity, CategoryDTO> {

    List<CategoryEntity> listWithTree();

    public Long[] findCategoryPath(Long categoryId);
}