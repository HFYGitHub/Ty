package com.ty.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.admin.web.dto.UserCreateRequest;
import com.ty.admin.web.dto.UserUpdateRequest;
import com.ty.admin.web.vo.UserVO;
import com.ty.common.entity.User;

public interface UserService extends IService<User> {


    Long createUser(UserCreateRequest request);


    String login(UserCreateRequest request);
}
