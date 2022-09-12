package com.windula.demoetl.exception;

public enum ExceptionEnum {


    DATABASE_PROPERTIES_FETCH(101, "Database properties fetch failed"),
    DATABASE_CONNECTION_FAILURE(102, "Database connection failed"),


    DATABASE_QUERY_FAILURE(103, "Database query failed");


    private int errorCode;
    private String errorMessage;

    private String errorState = null;

    ExceptionEnum(int errorCode, String errorMessage, String errorState) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorState = errorState;
    }

    ExceptionEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorState() {
        return errorState;
    }
}
