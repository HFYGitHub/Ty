package com.ty.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ty.admin.service.UserService;
import com.ty.admin.web.dto.UserCreateRequest;
import com.ty.admin.web.dto.UserUpdateRequest;
import com.ty.admin.web.vo.UserVO;
import com.ty.common.web.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     * 使用 RegisterGroup 分组：会校验 userName, pwd, repeatPwd
     */
    @PostMapping("/register")
    public Result<String> create(@Validated(UserCreateRequest.RegisterGroup.class) @RequestBody UserCreateRequest request) {
        try {
            Boolean data = userService.createUser(request) > 0;
            return Result.success("登录成功");
        } catch (Exception e) {
            log.error("登录失败", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 登录
     * 使用 LoginGroup 分组：只会校验 userName, pwd
     */
    @PostMapping("/login")
    public Result<String> login(@Validated(UserCreateRequest.LoginGroup.class) @RequestBody UserCreateRequest request) {
        try {
            String data = userService.login(request);
            return Result.success("登录成功",data);
        } catch (Exception e) {
            log.error("登录失败", e);
            return Result.fail(e.getMessage());
        }
    }

}

