package com.example.gulimall.ware.controller;

import com.example.gulimall.common.annotation.LogOperation;
import com.example.gulimall.common.constant.Constant;
import com.example.gulimall.common.page.PageData;
import com.example.gulimall.common.utils.Result;
import com.example.gulimall.common.validator.AssertUtils;
import com.example.gulimall.common.validator.ValidatorUtils;
import com.example.gulimall.common.validator.group.AddGroup;
import com.example.gulimall.common.validator.group.DefaultGroup;
import com.example.gulimall.common.validator.group.UpdateGroup;
import com.example.gulimall.ware.dto.WareOrderTaskDetailDTO;
import com.example.gulimall.ware.service.WareOrderTaskDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 库存工作单
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@RestController
@RequestMapping("ware/wareordertaskdetail")
@Api(tags="库存工作单")
public class WareOrderTaskDetailController {
    @Autowired
    private WareOrderTaskDetailService wareOrderTaskDetailService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<WareOrderTaskDetailDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<WareOrderTaskDetailDTO> page = wareOrderTaskDetailService.page(params);

        return new Result<PageData<WareOrderTaskDetailDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<WareOrderTaskDetailDTO> get(@PathVariable("id") Long id){
        WareOrderTaskDetailDTO data = wareOrderTaskDetailService.get(id);

        return new Result<WareOrderTaskDetailDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody WareOrderTaskDetailDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        wareOrderTaskDetailService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody WareOrderTaskDetailDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        wareOrderTaskDetailService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        wareOrderTaskDetailService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<WareOrderTaskDetailDTO> list = wareOrderTaskDetailService.list(params);

    }

}