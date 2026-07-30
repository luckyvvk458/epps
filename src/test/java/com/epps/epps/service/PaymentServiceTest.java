package com.epps.epps.service;

import com.epps.epps.dto.request.PaymentRequest;
import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.enums.PaymentMode;
import com.epps.epps.processor.*;
import com.epps.epps.repository.PaymentTransactionRepository;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import com.epps.epps.model.Payment;

class PaymentServiceTest {
    private final PaymentService service = new PaymentService(new PaymentProcessorRegistry(List.of(
        new CardPaymentProcessor(), new UPIPaymentProcessor(), new WalletPaymentProcessor())), mock(PaymentTransactionRepository.class));

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

    @Test
    void refusesToBuildAPaymentWithoutAMode() {
        assertThatThrownBy(() -> Payment.builder(new BigDecimal("125.50"), "INR", "customer-1").build())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Payment mode cannot be null");
    }

    private PaymentRequest request(PaymentMode mode) {
        return new PaymentRequest(new BigDecimal("125.50"), "INR", "customer-1", "merchant-1", null, null, null, 0, mode);
    }
}
