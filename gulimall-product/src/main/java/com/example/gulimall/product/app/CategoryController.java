package com.example.gulimall.product.app;

import com.example.gulimall.common.annotation.LogOperation;
import com.example.gulimall.common.constant.Constant;
import com.example.gulimall.common.page.PageData;
import com.example.gulimall.common.utils.Result;
import com.example.gulimall.common.validator.AssertUtils;
import com.example.gulimall.common.validator.ValidatorUtils;
import com.example.gulimall.common.validator.group.AddGroup;
import com.example.gulimall.common.validator.group.DefaultGroup;
import com.example.gulimall.common.validator.group.UpdateGroup;
import com.example.gulimall.product.dto.CategoryDTO;
import com.example.gulimall.product.entity.CategoryEntity;
import com.example.gulimall.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 商品三级分类
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@RestController
@RequestMapping("product/category")
@Api(tags = "商品三级分类")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @GetMapping("test")
    public void cacheCategory() {
        // 抢占锁 // 设置过期时间防止死锁
        String uuid = UUID.randomUUID().toString();
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent("local", uuid, 300, TimeUnit.SECONDS);
        if (aBoolean) {
            try {
                log.info("加锁成功");
                extracted();
            } finally {
                //释放锁
                // 获取值对比+对比成功=原子操作 lua脚本解锁
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                Integer local = stringRedisTemplate.execute(new DefaultRedisScript<Integer>(script), Arrays.asList("local"), Arrays.asList(uuid));
//            String local = stringRedisTemplate.opsForValue().get("local");
//            if (local != null && local.equals(uuid)) {
//                stringRedisTemplate.delete("local");
//            }
            }
        } else {
            log.info("加锁失败");
            cacheCategory();
        }
    }

    private void extracted() {
        String name = stringRedisTemplate.opsForValue().get("name");
        if (name == null) {
            name = "";
        }
        if (StringUtils.isEmpty(name)) {
            name = stringRedisTemplate.opsForValue().get("name");
            if (!StringUtils.isEmpty(name)) {
                log.info("缓存锁中命中");
                log.info(name);
                return;
            }
            log.info("查询了数据库");
            stringRedisTemplate.opsForValue().set("name", "zhangsan", 60, TimeUnit.SECONDS);
        }
        log.info("缓存命中");
        log.info(name);
    }

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<CategoryDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<CategoryDTO> page = categoryService.page(params);

        return new Result<PageData<CategoryDTO>>().ok(page);
    }

    @Cacheable(value = {"category"},key = "'levelCategorys'")
    @GetMapping("list/tree")
    public Result<List<CategoryEntity>> listTree() {
        log.info("查询所有分类");
        List<CategoryEntity> categoryEntities = categoryService.listWithTree();
        return new Result<List<CategoryEntity>>().ok(categoryEntities);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<CategoryDTO> get(@PathVariable("id") Long id) {
        CategoryDTO data = categoryService.get(id);

        return new Result<CategoryDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody CategoryDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        categoryService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody CategoryDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        categoryService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        categoryService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CategoryDTO> list = categoryService.list(params);

    }

}