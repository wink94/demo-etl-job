package com.windula.demoetl.exception;

import org.springframework.http.HttpStatus;

public class APIException extends DemoETLException {

    private final HttpStatus httpStatus ;

    public APIException(ExceptionEnum exceptionEnum) {

        super(exceptionEnum);

        this.httpStatus = null;
    }

    public APIException(ExceptionEnum exceptionEnum, Throwable cause, HttpStatus httpStatus) {

        super(exceptionEnum,cause);

        this.httpStatus = httpStatus;
    }


    public APIException(ExceptionEnum exceptionEnum, HttpStatus httpStatus) {

        super(exceptionEnum);

        this.httpStatus = httpStatus;
    }

    public APIException(ExceptionEnum exceptionEnum, Throwable cause) {

        super(exceptionEnum,cause);

        this.httpStatus = null;

    }


    public boolean isContainedHTTPStatus (HttpStatus httpStatus) {

        if(this.httpStatus==null){
            return  false;
        }else {
            return this.httpStatus==httpStatus;
        }

    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }
}
