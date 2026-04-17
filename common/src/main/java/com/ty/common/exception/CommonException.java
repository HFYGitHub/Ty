package com.ty.common.exception;

public class CommonException extends RuntimeException {
    private final int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CommonException(String message) {
        super(message);
        this.code = CommonErrorCode.BAD_REQUEST.getCode();
    }

    public CommonException(ErrorCode errorCode) {
        super(errorCode == null ? null : errorCode.getMessage());
        this.code = errorCode == null ? 0 : errorCode.getCode();
    }

    public CommonException(ErrorCode errorCode, String overrideMessage) {
        super(overrideMessage);
        this.code = errorCode == null ? 0 : errorCode.getCode();
    }

    public CommonException(ErrorCode errorCode, Throwable cause) {
        super(errorCode == null ? null : errorCode.getMessage(), cause);
        this.code = errorCode == null ? 0 : errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}

