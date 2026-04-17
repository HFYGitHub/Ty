package com.ty.app.service;

import com.ty.app.web.dto.LoginRequest;
import com.ty.app.web.vo.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}

