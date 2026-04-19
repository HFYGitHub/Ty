package com.ty.admin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateRequest {

    // 定义校验分组
    public interface LoginGroup {}
    public interface RegisterGroup {}

    /**
     * 用户名：登录和注册都需要校验
     */
    @NotBlank(message = "用户名不能为空", groups = {LoginGroup.class, RegisterGroup.class})
    private String userName;

    /**
     * 用户密码：登录和注册都需要校验
     */
    @NotBlank(message = "用户密码不能为空", groups = {LoginGroup.class, RegisterGroup.class})
    private String pwd;

    /**
     * 用户二次输入密码：仅在注册时校验
     */
    @NotBlank(message = "确认密码不能为空", groups = {RegisterGroup.class})
    private String repeatPwd;
}
