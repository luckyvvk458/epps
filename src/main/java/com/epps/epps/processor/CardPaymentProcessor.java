package com.epps.epps.processor;

import com.epps.epps.model.Payment;
import com.epps.epps.enums.PaymentMode;
import com.epps.epps.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class CardPaymentProcessor implements PaymentProcessor{
    @Override public PaymentMode supports() { return PaymentMode.CARD; }
    @Override
    public PaymentResponse process(Payment payment) {
        return new PaymentResponse("card-" + java.util.UUID.randomUUID(), "SUCCESS", "Card payment processed successfully");
    }
}
