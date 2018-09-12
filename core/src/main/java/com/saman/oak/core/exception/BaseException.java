package com.saman.oak.core.exception;

public class BaseException extends RuntimeException {

    private String message;
    private String code;

    public BaseException() {
        init();
    }

    public void init() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public BaseException setCode(String code) {
        this.code = code;
        return this;
    }

    public BaseException setMessage(String message) {
        this.message = message;
        return this;
    }
}
