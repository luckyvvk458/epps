package com.epps.epps.processor;

import com.epps.epps.model.Payment;

public class CardPaymentProcessor implements PaymentProcessor{
    @Override
    public void process(Payment payment) {
        System.out.println("Card Payment processing...");
    }
}
