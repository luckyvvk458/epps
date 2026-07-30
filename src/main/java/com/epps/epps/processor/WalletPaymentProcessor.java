package com.epps.epps.processor;

import com.epps.epps.model.Payment;
import com.epps.epps.enums.PaymentMode;
import com.epps.epps.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class WalletPaymentProcessor implements PaymentProcessor{
    @Override public PaymentMode supports() { return PaymentMode.WALLET; }
    @Override
    public PaymentResponse process(Payment payment) {
        return new PaymentResponse("wallet-" + java.util.UUID.randomUUID(), "SUCCESS", "Wallet payment processed successfully");
    }
}
