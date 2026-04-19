package com.ty.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.admin.constant.AdminConfigConst;
import com.ty.admin.mapper.UserMapper;
import com.ty.admin.service.UserService;
import com.ty.admin.utils.RedisUtil;
import com.ty.admin.web.dto.UserCreateRequest;
import com.ty.admin.web.dto.UserUpdateRequest;
import com.ty.admin.web.vo.UserVO;
import com.ty.common.config.JwtProperties;
import com.ty.common.entity.User;
import com.ty.common.exception.CommonErrorCode;
import com.ty.common.exception.CommonException;
import com.ty.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtProperties jwtProperties;

    private final RedisUtil redisUtil;


    @Override
    public Long createUser(UserCreateRequest request) {
        User oldUser = baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, request.getUserName())
                .last("limit 1"));
        if (oldUser != null) {
            throw new CommonException(CommonErrorCode.BAD_REQUEST, "用户已存在");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setPwd(passwordEncoder.encode(request.getPwd()));
        boolean ok = save(user);
        if (!ok) {
            throw new CommonException(CommonErrorCode.INTERNAL_ERROR, "创建用户失败");
        }
        return user.getId();
    }


    @Override
    public String login(UserCreateRequest request) {
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, request.getUserName())
               .last("limit 1"));

        if (user != null && passwordEncoder.matches(request.getPwd(), user.getPwd())) {
            Map<String,Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            claims.put("username", user.getUserName());

            Date expireDate = new Date(System.currentTimeMillis() + jwtProperties.getExpire() * 60 * 1000);
            String token = JwtUtil.createToken(jwtProperties.getSecret(), claims, expireDate);

            //登录信息存redis
            String redisKey = String.format(AdminConfigConst.LOGIN_TOKEN, user.getId());
            redisUtil.set(redisKey, token, jwtProperties.getExpire() * 60);
            return token;
        }else {
            throw new CommonException(CommonErrorCode.BAD_REQUEST, "登录账号密码错误");
        }
    }


}
