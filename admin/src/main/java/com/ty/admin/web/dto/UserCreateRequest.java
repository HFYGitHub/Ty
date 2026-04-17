package com.ty.admin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateRequest {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String pwd;

    /**
     * 用户二次输入密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String repeatPwd;
}
