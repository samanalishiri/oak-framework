package com.saman.oak.core.exception;

public enum ExceptionEnum {

    NULL("the Object is null", "exception-1000"),
    ;

    private final String message;
    private final String code;

    ExceptionEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String message() {
        return message;
    }


    public String code() {
        return code;
    }


}
