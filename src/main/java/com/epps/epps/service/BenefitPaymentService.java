package com.epps.epps.service;

import com.epps.epps.dto.request.BenefitPaymentRequest;
import com.epps.epps.dto.response.BenefitPaymentResponse;
import com.epps.epps.model.BenefitPaymentTransaction;
import com.epps.epps.processor.BenefitGatewayRegistry;
import com.epps.epps.processor.BenefitPaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class BenefitPaymentService {

    private final BenefitValidator benefitValidator;
    private final BenefitPaymentTransactionService transactionService;
    private final BenefitGatewayRegistry gatewayRegistry;
    private final BenefitPaymentEventPublisher eventPublisher;

    public BenefitPaymentService(BenefitValidator benefitValidator,
                                 BenefitPaymentTransactionService transactionService,
                                 BenefitGatewayRegistry gatewayRegistry,
                                 BenefitPaymentEventPublisher eventPublisher) {
        this.benefitValidator = benefitValidator;
        this.transactionService = transactionService;
        this.gatewayRegistry = gatewayRegistry;
        this.eventPublisher = eventPublisher;
    }

    public BenefitPaymentResponse processBenefit(BenefitPaymentRequest request) {

        // Step 1 : Validate benefit payment request
        benefitValidator.validate(request);

        // Step 2 : Create transaction with PENDING status
        BenefitPaymentTransaction transaction =
                transactionService.createTransaction(request);

        try {

            // Step 3 : Select payment gateway
            BenefitPaymentGateway gateway =
                    gatewayRegistry.resolve(request.paymentGateway());

            // Step 4 : Execute payment
            BenefitPaymentResponse response =
                    gateway.transfer(request);

            // Step 5 : Update transaction
            transactionService.markSuccess(
                    transaction,
                    response);

            // Step 6 : Publish payment completed event
            eventPublisher.publishSuccess(response);

            return response;

        } catch (Exception ex) {

            // Step 7 : Update failed transaction
            transactionService.markFailed(
                    transaction,
                    ex.getMessage());

            // Step 8 : Publish failure event
            eventPublisher.publishFailure(transaction.getTransactionId(), ex.getMessage());

            throw ex;
        }
    }

}