package com.gcc.zhxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gcc.zhxy.pojo.Admin;
import com.gcc.zhxy.pojo.LoginForm;

public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin getAdminById(long userId);
}
