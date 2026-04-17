package com.ty.app.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotBlank
    @Size(max = 64)
    private String userName;

    @NotBlank
    @Size(max = 128)
    private String pwd;
}

