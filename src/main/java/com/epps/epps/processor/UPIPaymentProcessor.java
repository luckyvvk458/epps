package com.epps.epps.processor;

import com.epps.epps.model.Payment;
import com.epps.epps.enums.PaymentMode;
import com.epps.epps.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class UPIPaymentProcessor implements PaymentProcessor{
    @Override public PaymentMode supports() { return PaymentMode.UPI; }
    @Override
    public PaymentResponse process(Payment payment) {
        return new PaymentResponse("upi-" + java.util.UUID.randomUUID(), "SUCCESS", "UPI payment processed successfully");
    }
}
