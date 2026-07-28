package com.epps.epps.processor;

import com.epps.epps.enums.PaymentMode;

public interface PaymentProcessorFactory {
    PaymentProcessor createPayment(PaymentMode paymentMode);
}
