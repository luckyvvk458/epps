package com.epps.epps.service;

import com.epps.epps.dto.request.PaymentRequest;
import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.model.Payment;
import com.epps.epps.processor.PaymentProcessorRegistry;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentProcessorRegistry processorRegistry;

    public PaymentService(PaymentProcessorRegistry processorRegistry) {
        this.processorRegistry = processorRegistry;
    }

    public PaymentResponse paymentResponse(PaymentRequest paymentRequest) {
        Payment payment = buildPayment(paymentRequest);
        return processorRegistry.forMode(payment.getPaymentMode()).process(payment);
    }

    private static Payment buildPayment(PaymentRequest paymentRequest) {
        return Payment
                .builder(paymentRequest.getAmount(),
                        paymentRequest.getCurrency(),
                        paymentRequest.getCustomerId())
                .merchantId(paymentRequest.getMerchantId())
                .coupon(paymentRequest.getCoupon())
                .description(paymentRequest.getDescription())
                .callbackUrl(paymentRequest.getCallbackUrl())
                .retryCount(paymentRequest.getRetryCount())
                .paymentMode(paymentRequest.getPaymentMode())
                .build();
    }
}
