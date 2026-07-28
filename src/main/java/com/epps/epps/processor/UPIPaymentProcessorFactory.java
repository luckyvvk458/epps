package com.epps.epps.processor;

import com.epps.epps.enums.PaymentMode;

public class UPIPaymentProcessorFactory implements PaymentProcessorFactory{
    @Override
    public PaymentProcessor createPayment(PaymentMode paymentMode) {
        return new UPIPaymentProcessor();
    }
}
