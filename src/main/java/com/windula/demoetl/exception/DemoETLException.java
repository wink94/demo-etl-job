package com.windula.demoetl.exception;

public class DemoETLException extends RuntimeException{

    private final ExceptionEnum exceptionEnum;


    public DemoETLException(ExceptionEnum exceptionEnum ,Throwable cause) {
        super(exceptionEnum.getErrorMessage(),cause);
        this.exceptionEnum = exceptionEnum;
    }

    public DemoETLException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getErrorMessage());
        this.exceptionEnum = exceptionEnum;
    }
}
