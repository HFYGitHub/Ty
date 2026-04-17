package com.ty.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ty.app.mapper.UserMapper;
import com.ty.app.service.AuthService;
import com.ty.app.web.dto.LoginRequest;
import com.ty.app.web.vo.LoginResponse;
import com.ty.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String TOKEN_KEY_PREFIX = "app:auth:token:";
    private static final String USER_TOKEN_KEY_PREFIX = "app:auth:user:";

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${ty.auth.token-ttl-seconds:604800}")
    private long tokenTtlSeconds;

    @Override
    public LoginResponse login(LoginRequest request) {
        Objects.requireNonNull(request, "request");
        String userName = request.getUserName() == null ? null : request.getUserName().trim();
        if (userName == null || userName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userName required");
        }

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, userName)
                .last("limit 1"));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials");
        }
        String encodedPwd = user.getPwd();
        if (encodedPwd == null || !passwordEncoder.matches(request.getPwd(), encodedPwd)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials");
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        Duration ttl = Duration.ofSeconds(Math.max(1, tokenTtlSeconds));

        // Single active token per user: overwrite old token mapping if exists.
        String userTokenKey = USER_TOKEN_KEY_PREFIX + user.getId();
        String oldToken = stringRedisTemplate.opsForValue().get(userTokenKey);
        if (oldToken != null && !oldToken.isEmpty()) {
            stringRedisTemplate.delete(TOKEN_KEY_PREFIX + oldToken);
        }

        stringRedisTemplate.opsForValue().set(TOKEN_KEY_PREFIX + token, String.valueOf(user.getId()), ttl);
        stringRedisTemplate.opsForValue().set(userTokenKey, token, ttl);

        LoginResponse resp = new LoginResponse();
        resp.setToken(token);
        resp.setUserId(user.getId());
        resp.setUserName(user.getUserName());
        return resp;
    }
}

