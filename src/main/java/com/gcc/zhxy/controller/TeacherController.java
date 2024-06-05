package com.gcc.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.zhxy.pojo.Teacher;
import com.gcc.zhxy.service.TeacherService;
import com.gcc.zhxy.util.MD5;
import com.gcc.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //sms/teacherController/deleteTeacher
    @ApiOperation("删除单个或者多个教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@ApiParam("要删除的教师信息的ID的JSON集合")@RequestBody List<Integer> ids) {
        teacherService.removeByIds(ids);
        return Result.ok();
    }


    //sms/teacherController/saveOrupdateTeacher
    @ApiOperation("增加或者修改教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrupdateTeacher(@ApiParam("要保存或者修改的JSON格式的Teacher对象")@RequestBody Teacher teacher) {

        if(teacher.getId() == null || teacher.getId() == 0) {
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    //sms/teacherController/getTeachers/1/3
    @ApiOperation("分页带条件查询教师信息")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @ApiParam("页码数") @PathVariable Integer pageNo,
            @ApiParam("页大小") @PathVariable Integer pageSize,
            @ApiParam("查询条件") Teacher teacher
    ) {
        Page<Teacher> pageParam = new Page<>(pageNo, pageSize);
        IPage<Teacher> iPage = teacherService.getTeachersByOpr(pageParam, teacher);
        return Result.ok(iPage);
    }
}
