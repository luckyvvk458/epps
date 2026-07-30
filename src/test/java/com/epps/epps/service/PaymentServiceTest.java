package com.epps.epps.service;

import com.epps.epps.dto.request.PaymentRequest;
import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.enums.PaymentMode;
import com.epps.epps.processor.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    private final PaymentService service = new PaymentService(new PaymentProcessorRegistry(List.of(
        new CardPaymentProcessor(), new UPIPaymentProcessor(), new WalletPaymentProcessor())));

    @Test
    void processesUsingTheRequestedStrategy() {
        PaymentResponse response = service.paymentResponse(request(PaymentMode.UPI));
        assertThat(response.getPaymentId()).startsWith("upi-");
        assertThat(response.getStatus()).isEqualTo("SUCCESS");
    }

    @Test
    void selectsEachSupportedPaymentMode() {
        assertThat(service.paymentResponse(request(PaymentMode.CARD)).getPaymentId()).startsWith("card-");
        assertThat(service.paymentResponse(request(PaymentMode.WALLET)).getPaymentId()).startsWith("wallet-");
    }

    private PaymentRequest request(PaymentMode mode) {
        return new PaymentRequest(new BigDecimal("125.50"), "INR", "customer-1", "merchant-1", null, null, null, 0, mode);
    }
}
