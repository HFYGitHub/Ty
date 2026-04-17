package com.ty.common.exception;

public enum CommonErrorCode implements ErrorCode {
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "没有权限"),
    FORBIDDEN(403, "拒绝请求"),
    NOT_FOUND(404, "未发现"),
    INTERNAL_ERROR(500, "网络异常");

    private final int code;
    private final String message;

    CommonErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

