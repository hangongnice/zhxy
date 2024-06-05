package com.gcc.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcc.zhxy.pojo.LoginForm;
import com.gcc.zhxy.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {
    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(long userId);

    IPage<Teacher> getTeachersByOpr(Page<Teacher> pageParam, Teacher teacher);
}
