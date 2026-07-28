package com.epps.epps.processor;

import com.epps.epps.model.Payment;

public interface PaymentProcessor {
    void process(Payment payment);
}
