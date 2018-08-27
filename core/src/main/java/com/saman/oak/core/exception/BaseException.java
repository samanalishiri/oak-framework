package com.saman.oak.core.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true, fluent = true)
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
}
