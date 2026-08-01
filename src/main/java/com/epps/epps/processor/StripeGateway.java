package com.epps.epps.processor;

import com.epps.epps.dto.request.BenefitPaymentRequest;
import com.epps.epps.dto.response.BenefitPaymentResponse;
import com.epps.epps.enums.PaymentGatewayType;
import org.springframework.stereotype.Component;

@Component
public class StripeGateway implements BenefitPaymentGateway {

    @Override
    public PaymentGatewayType getGatewayType() {
        return PaymentGatewayType.STRIPE;
    }

    @Override
    public BenefitPaymentResponse transfer(BenefitPaymentRequest request) {

        return BenefitPaymentResponse.builder()
                .transactionId("TXN-123")
                .benefitId(request.benefitId())
                .employeeId(request.employeeId())
                .amount(request.amount())
                .currency(request.currency())
                .paymentGateway(PaymentGatewayType.STRIPE)
                .gatewayReferenceId("STRIPE-987654")
                .message("Payment processed successfully")
                .build();
    }
}