package com.bookstore.exception;

public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }

    public InvalidParameterException(String errorMessage){
        super(errorMessage);
    }
}
