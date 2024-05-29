package com.gcc.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcc.zhxy.pojo.Student;
import com.gcc.zhxy.service.StudentService;
import com.gcc.zhxy.util.MD5;
import com.gcc.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生管理器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("删除单个或者多个学生信息")
    @DeleteMapping("/delStudentById")
    public Result delStudentById(@ApiParam("要删除的学生的变化的JSON集合") @RequestBody List<Integer> ids) {

        studentService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("新增或者修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@ApiParam("要保持或者修改的学生的JSON") @RequestBody Student student) {
        Integer id = student.getId();
        if (null == id || 0 == id) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }



    @ApiOperation("分页带条件查询学生信息")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(

            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize")Integer pageSize,
            @ApiParam("查询条件") Student student
    ) {

        //分页信息封装Page对象
        Page<Student> pageParam = new Page<>(pageNo, pageSize);
        //进行查询
        IPage<Student> studentIPage = studentService.getStudentByOpr(pageParam, student);
        //封装Result对象
        return Result.ok(studentIPage);
        }
    }

