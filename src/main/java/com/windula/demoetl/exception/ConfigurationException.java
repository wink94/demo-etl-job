package com.windula.demoetl.exception;

public class ConfigurationException extends DemoETLException {

    public ConfigurationException(ExceptionEnum exceptionEnum){
        super(exceptionEnum);
    }

    public ConfigurationException(ExceptionEnum exceptionEnum, Throwable cause){
        super(exceptionEnum, cause);
    }
}