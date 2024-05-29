package com.gcc.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcc.zhxy.pojo.LoginForm;
import com.gcc.zhxy.pojo.Student;

public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student getStudentById(long userId);

    IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student);
}
