package com.epps.epps.processor;

import com.epps.epps.dto.request.BenefitPaymentRequest;
import com.epps.epps.dto.response.BenefitPaymentResponse;
import com.epps.epps.enums.PaymentGatewayType;

public interface BenefitPaymentGateway {

    PaymentGatewayType getGatewayType();

    BenefitPaymentResponse transfer(BenefitPaymentRequest request);
}