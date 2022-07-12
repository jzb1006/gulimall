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
        SkuModel skuModel = new SkuModel();
        skuModel.setSkuId(19L);
        skuModel.setSpuId(16L);
        skuModel.setSkuTitle("华为 MATE 16 Pro 超级续航安全手机");
        skuModel.setSkuPrice(new BigDecimal("6000"));
        skuModel.setSkuImg("http://www.baidu.com");
        skuModel.setSaleCount(9L);
        skuModel.setHasStock(true);
        skuModel.setHotScore(30L);
        skuModel.setBrandId(100L);
        skuModel.setCatalogId(10L);
        skuModel.setBrandName("华为");
        skuModel.setBrandImg("http://www.huawei.com");
        skuModel.setCatalogName("超级续航安全手机");

        SkuModel.Attr attr = new SkuModel.Attr();
        attr.setAttrId(1L);
        attr.setAttrName("Intel处理器");
        attr.setAttrValue("I9-9300");

        SkuModel.Attr attr2 = new SkuModel.Attr();
        attr2.setAttrId(2L);
        attr2.setAttrName("Amd理器");
        attr2.setAttrValue("777");

        List<SkuModel.Attr> attrs = new ArrayList<>();
        System.out.println(attrs);
        attrs.add(attr2);
        attrs.add(attr);

        skuModel.setAttrs(attrs);

        List<SkuModel> list = new ArrayList<>();
        list.add(skuModel);
        System.out.println("=======");
        System.out.println(list);
        Result<Boolean> booleanResult = service.productSave(list);
        System.out.println(booleanResult.getData());
        System.out.println(booleanResult.getCode());
        System.out.println(booleanResult.getMsg());
        if (booleanResult.getData()){
            // todo 上传成功后需要去修改商品状态
        }else {
            // todo 调用重复？接口幂等性；重试机制。
        }
    }
}