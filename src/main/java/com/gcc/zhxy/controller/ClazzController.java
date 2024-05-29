package com.gcc.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.zhxy.pojo.Clazz;
import com.gcc.zhxy.service.ClazzService;
import com.gcc.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班级管理器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    ///sms/clazzController/getClazzs
    @ApiOperation("查询所有班级信息")
    @GetMapping("/getClazzs")
    public Result getClazzs() {

        List<Clazz> clazzs = clazzService.getClazzs();
        return Result.ok(clazzs);
    }


    /**
     * 删除单个或者多个信息
     */
    @ApiOperation("删除单个或者多个班级信息")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@ApiParam("要删除的班级的ID的JSON数组") @RequestBody List<Integer> ids) {
        clazzService.removeByIds(ids);
        return Result.ok();
    }

    /**
     * 增加或者修改班级信息
     */
    @ApiOperation("增加或者修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@ApiParam("JSON格式的班级信息") @RequestBody Clazz clazz){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    /**
     * 分页带条件查询班级信息
     */

    @ApiOperation("分页带条件查询班级信息")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzByopr(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大选") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("页大选") Clazz clazz
            ){

        //设置分页信息
        Page<Clazz> page = new Page<>(pageNo, pageSize);
        IPage<Clazz> iPage = clazzService.getClazzsByOpr(page, clazz);
        return Result.ok(iPage);
    }
}
