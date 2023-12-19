package com.bookstore.exception;

public class InvalidVerificationCodeException extends Exception{
    public InvalidVerificationCodeException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }

    public InvalidVerificationCodeException(String errorMessage){
        super(errorMessage);
    }
}
