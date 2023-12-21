package com.bookstore.exception;

public class InvalidPaymentMethodException extends RuntimeException{
    public InvalidPaymentMethodException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }

    public InvalidPaymentMethodException(String errorMessage){
        super(errorMessage);
    }
}
