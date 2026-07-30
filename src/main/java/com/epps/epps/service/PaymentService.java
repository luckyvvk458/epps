package com.epps.epps.service;

import com.epps.epps.dto.request.PaymentRequest;
import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.model.Payment;
import com.epps.epps.processor.PaymentProcessorRegistry;
import com.epps.epps.model.PaymentTransaction;
import com.epps.epps.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    private final PaymentProcessorRegistry processorRegistry;
    private final PaymentTransactionRepository transactionRepository;

    public PaymentService(PaymentProcessorRegistry processorRegistry, PaymentTransactionRepository transactionRepository) {
        this.processorRegistry = processorRegistry;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public PaymentResponse paymentResponse(PaymentRequest paymentRequest) {
        Payment payment = buildPayment(paymentRequest);
        PaymentTransaction transaction = new PaymentTransaction(payment);
        transactionRepository.save(transaction);
        PaymentResponse response = processorRegistry.forMode(payment.getPaymentMode()).process(payment);
        transaction.markSuccessful();
        return response;
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
