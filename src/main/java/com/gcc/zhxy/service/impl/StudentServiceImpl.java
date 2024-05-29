package com.gcc.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcc.zhxy.mapper.StudentMapper;
import com.gcc.zhxy.pojo.LoginForm;
import com.gcc.zhxy.pojo.Student;
import com.gcc.zhxy.service.StudentService;
import com.gcc.zhxy.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("stuService")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Student student = baseMapper.selectOne(queryWrapper);
        return student;

    }

    @Override
    public Student getStudentById(long userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return baseMapper.selectOne(queryWrapper);
    }


    @Override
    public IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(student.getName())) {
            studentQueryWrapper.like("name", student.getName());
        }
        if (!StringUtils.isEmpty(student.getClazzName())) {
           studentQueryWrapper.like("clazz_name", student.getClazzName());
        }
        studentQueryWrapper.orderByDesc("id");
        Page<Student> studentIPage = baseMapper.selectPage(pageParam, studentQueryWrapper);

        return studentIPage;
    }
}