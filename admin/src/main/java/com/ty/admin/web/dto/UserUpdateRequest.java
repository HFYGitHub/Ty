package com.ty.admin.web.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {

    @Size(max = 64)
    private String userName;

    @Size(max = 128)
    private String pwd;
}

