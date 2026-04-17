package com.ty.app.web.vo;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Long userId;
    private String userName;
}

