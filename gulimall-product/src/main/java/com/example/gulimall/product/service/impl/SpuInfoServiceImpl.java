package com.example.gulimall.product.service.impl;
import com.example.gulimall.common.utils.Result;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.dto.es.SkuModel;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.product.dao.SpuInfoDao;
import com.example.gulimall.product.dto.SkuModelDto;
import com.example.gulimall.product.dto.SpuInfoDTO;
import com.example.gulimall.product.entity.SpuInfoEntity;
import com.example.gulimall.product.feign.SearchFeignService;
import com.example.gulimall.product.service.SpuInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * spu信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class SpuInfoServiceImpl extends CrudServiceImpl<SpuInfoDao, SpuInfoEntity, SpuInfoDTO> implements SpuInfoService {

    @Autowired
    SearchFeignService service;

    @Override
    public QueryWrapper<SpuInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public void up(Long spuId) {
        // todo 查询到商的相关信息 然后通过search的服务保存
        SkuModelDto skuModel = new SkuModelDto();
        skuModel.setSkuId(1L);
        skuModel.setSpuId(1L);
        skuModel.setSkuTitle("小米11红色");
        skuModel.setSkuPrice(new BigDecimal("1000"));
        skuModel.setSkuImg("http://www.baidu.com");
        skuModel.setSaleCount(true);
        skuModel.setHasStock(1000L);
        skuModel.setHotScore(10L);
        skuModel.setBrandId(40L);
        skuModel.setCatalogId(20L);
        skuModel.setBrandName("小米");
        skuModel.setBrandImg("http://www.baidu.com");
        skuModel.setCatalogName("智能手机");

        SkuModelDto.Attr attr = new SkuModelDto.Attr();
        attr.setAttrId(10L);
        attr.setAttrName(20L);
        attr.setAttrValue(50L);

        SkuModelDto.Attr attr2 = new SkuModelDto.Attr();
        attr2.setAttrId(1L);
        attr2.setAttrName(2L);
        attr2.setAttrValue(6L);

        List<SkuModelDto.Attr> attrs = new ArrayList<>();
        System.out.println(attrs);
        attrs.add(attr2);
        attrs.add(attr);

        skuModel.setAttrs(attrs);

        List<SkuModelDto> list = new ArrayList<>();
        list.add(skuModel);
        System.out.println("=======");
        System.out.println(list);
        Result<Boolean> booleanResult = service.productSave(list);
        if (booleanResult.getData()){
            // todo 上传成功后需要去修改商品状态
        }else {
            // todo 调用重复？接口幂等性；重试机制。
        }
    }
}