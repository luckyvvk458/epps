package com.epps.epps.service;

import com.epps.epps.dto.response.BenefitPaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BenefitPaymentEventPublisher {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(BenefitPaymentEventPublisher.class);

    public void publishSuccess(BenefitPaymentResponse response) {

        LOGGER.info(
                "Payment Success Event -> TransactionId: {}, EmployeeId: {}, BenefitId: {}, Gateway: {}",
                response.getTransactionId(),
                response.getEmployeeId(),
                response.getBenefitId(),
                response.getPaymentGateway()
        );
    }

    public void publishFailure(String transactionId, String reason) {

        LOGGER.error(
                "Payment Failure Event -> TransactionId: {}, Reason: {}",
                transactionId,
                reason
        );
    }
}
