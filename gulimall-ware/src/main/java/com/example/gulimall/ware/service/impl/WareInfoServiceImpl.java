package com.example.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.ware.dao.WareInfoDao;
import com.example.gulimall.ware.dto.WareInfoDTO;
import com.example.gulimall.ware.entity.WareInfoEntity;
import com.example.gulimall.ware.service.WareInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class WareInfoServiceImpl extends CrudServiceImpl<WareInfoDao, WareInfoEntity, WareInfoDTO> implements WareInfoService {

    @Override
    public QueryWrapper<WareInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WareInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}