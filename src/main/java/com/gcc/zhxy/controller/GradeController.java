package com.gcc.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.zhxy.pojo.Grade;
import com.gcc.zhxy.service.GradeService;
import com.gcc.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {


    @Autowired
    private GradeService gradeService;

    @ApiOperation("获取全部年级")
    @GetMapping("/getGrades")
    public Result getGrades() {


        List<Grade> grades = gradeService.getGrades();
        return Result.ok(grades);
    }

    @ApiOperation("删除Grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@ApiParam("要删除的所有的Grade的id的JSON集合") @RequestBody List<Integer> ids) {
        // 调用服务层删除
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    @ApiParam("新增或者修改Grade")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("JSON格式的Grade对象") @RequestBody Grade grade) {
        // 调用服务层的方法完成增加或修改
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    @ApiOperation("根据年级名称模糊查询，带分页")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(


            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页查询模糊名称匹配") String gradeName
    ){
        // 分页带条件查询
        Page<Grade> page = new Page<>(pageNo, pageSize);
        // 通过服务层进行查询
        IPage<Grade> pageRS = gradeService.getGradeByOpr(page, gradeName);
        // 封装Result对象并返回
        return Result.ok(pageRS);
    }
}