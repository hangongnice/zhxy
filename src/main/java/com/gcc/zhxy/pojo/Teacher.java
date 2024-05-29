package com.gcc.zhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @project:sms
 * @description:教师信息
 */

@Data
@TableName("tb_teacher")
public class Teacher {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String tno;
    private String name;
    private char gender;
    private String password;
    private String telephone;
    private String email;
    private String address;
    private String portraitPath;
    private String clazzName;


}

