package com.example.gulimall.member.controller;

import com.example.gulimall.common.annotation.LogOperation;
import com.example.gulimall.common.constant.Constant;
import com.example.gulimall.common.page.PageData;
import com.example.gulimall.common.utils.Result;
import com.example.gulimall.common.validator.AssertUtils;
import com.example.gulimall.common.validator.ValidatorUtils;
import com.example.gulimall.common.validator.group.AddGroup;
import com.example.gulimall.common.validator.group.DefaultGroup;
import com.example.gulimall.common.validator.group.UpdateGroup;
import com.example.gulimall.member.dto.MemberReceiveAddressDTO;
import com.example.gulimall.member.service.MemberReceiveAddressService;
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
 * 会员收货地址
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@RestController
@RequestMapping("member/memberreceiveaddress")
@Api(tags="会员收货地址")
public class MemberReceiveAddressController {
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<MemberReceiveAddressDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<MemberReceiveAddressDTO> page = memberReceiveAddressService.page(params);

        return new Result<PageData<MemberReceiveAddressDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<MemberReceiveAddressDTO> get(@PathVariable("id") Long id){
        MemberReceiveAddressDTO data = memberReceiveAddressService.get(id);

        return new Result<MemberReceiveAddressDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody MemberReceiveAddressDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        memberReceiveAddressService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody MemberReceiveAddressDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        memberReceiveAddressService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        memberReceiveAddressService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<MemberReceiveAddressDTO> list = memberReceiveAddressService.list(params);

    }

}