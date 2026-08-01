package com.epps.epps.exception;

public class PaymentGatewayException extends PaymentException {

    public PaymentGatewayException(String message) {
        super(message);
    }

    public PaymentGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}