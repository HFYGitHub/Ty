package com.ty.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ty.admin.service.UserService;
import com.ty.admin.web.dto.UserCreateRequest;
import com.ty.admin.web.dto.UserUpdateRequest;
import com.ty.admin.web.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Long create(@Valid @RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody UserCreateRequest request) {
        return userService.login(request);
    }


}

