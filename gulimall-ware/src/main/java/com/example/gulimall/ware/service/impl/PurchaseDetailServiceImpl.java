package com.example.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.ware.dao.PurchaseDetailDao;
import com.example.gulimall.ware.dto.PurchaseDetailDTO;
import com.example.gulimall.ware.entity.PurchaseDetailEntity;
import com.example.gulimall.ware.service.PurchaseDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class PurchaseDetailServiceImpl extends CrudServiceImpl<PurchaseDetailDao, PurchaseDetailEntity, PurchaseDetailDTO> implements PurchaseDetailService {

    @Override
    public QueryWrapper<PurchaseDetailEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}