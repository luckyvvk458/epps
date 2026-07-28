package com.epps.epps.processor;

import com.epps.epps.model.Payment;

public class WalletPaymentProcessor implements PaymentProcessor{
    @Override
    public void process(Payment payment) {
        System.out.println("Wallet Payment processing...");
    }
}
