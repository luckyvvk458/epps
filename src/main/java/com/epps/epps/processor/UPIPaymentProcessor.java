package com.epps.epps.processor;

import com.epps.epps.model.Payment;

public class UPIPaymentProcessor implements PaymentProcessor{
    @Override
    public void process(Payment payment) {
        System.out.println("Processing UPI Payment");
    }
}
