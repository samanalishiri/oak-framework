package com.saman.oak.core.exception;

public enum HibernateExceptionEnum {

    SESSION_FACTORY_NULL("the session factory is null", "hibernate-1000", ExceptionEnum.NULL),
    SESSION_NULL("the session is null", "hibernate-1001", ExceptionEnum.NULL),;

    private final String message;
    private final String code;
    private final ExceptionEnum exception;

    HibernateExceptionEnum(String message, String code, ExceptionEnum exception) {
        this.message = message;
        this.code = code;
        this.exception = exception;
    }

    public String message() {
        return message;
    }

    public String code() {
        return code;
    }

    public ExceptionEnum exception() {
        return exception;
    }

}
