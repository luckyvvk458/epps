package com.epps.epps.processor;

import com.epps.epps.model.Payment;
import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.enums.PaymentMode;

public interface PaymentProcessor {
    PaymentMode supports();
    PaymentResponse process(Payment payment);
}
