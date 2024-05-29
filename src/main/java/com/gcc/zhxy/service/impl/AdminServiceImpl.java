package com.gcc.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcc.zhxy.mapper.AdminMapper;
import com.gcc.zhxy.pojo.Admin;
import com.gcc.zhxy.pojo.LoginForm;
import com.gcc.zhxy.service.AdminService;
import com.gcc.zhxy.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin getAdminById(long userId) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return baseMapper.selectOne(queryWrapper);
    }
}
