package com.epps.epps.service;

import com.epps.epps.dto.request.BenefitPaymentRequest;
import com.epps.epps.dto.response.BenefitPaymentResponse;
import com.epps.epps.exception.PaymentValidationException;
import com.epps.epps.model.BenefitPaymentTransaction;
import com.epps.epps.enums.PaymentStatus;
import com.epps.epps.repository.BenefitPaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BenefitPaymentTransactionService {

    private final BenefitPaymentRepository repository;

    public BenefitPaymentTransactionService(BenefitPaymentRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new payment transaction before invoking the payment gateway.
     */
    public BenefitPaymentTransaction createTransaction(BenefitPaymentRequest request) {

        BenefitPaymentTransaction transaction = new BenefitPaymentTransaction();

        transaction.setTransactionId(generateTransactionId());
        transaction.setBenefitId(request.benefitId());
        transaction.setEmployeeId(request.employeeId());
        transaction.setAmount(request.amount());
        transaction.setCurrency(request.currency());

        transaction.setStatus(PaymentStatus.PENDING);

        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());

        return repository.save(transaction);
    }

    /**
     * Marks a payment transaction as successful.
     */
    public void markSuccess(BenefitPaymentTransaction transaction,
                            BenefitPaymentResponse response) {

        transaction.setStatus(PaymentStatus.SUCCESS);
        transaction.setGateway(response.getPaymentGateway());
        transaction.setGatewayReferenceId(response.getGatewayReferenceId());
        transaction.setUpdatedAt(LocalDateTime.now());

        repository.save(transaction);
    }

    /**
     * Marks a payment transaction as failed.
     */
    public void markFailed(BenefitPaymentTransaction transaction,
                           String failureReason) {

        transaction.setStatus(PaymentStatus.FAILED);
        transaction.setFailureReason(failureReason);
        transaction.setUpdatedAt(LocalDateTime.now());

        repository.save(transaction);
    }

    /**
     * Marks a payment transaction for retry.
     */
    public void markRetry(BenefitPaymentTransaction transaction,
                          String failureReason) {

        transaction.setStatus(PaymentStatus.RETRY_PENDING);
        transaction.setFailureReason(failureReason);
        transaction.setUpdatedAt(LocalDateTime.now());

        repository.save(transaction);
    }

    /**
     * Retrieves a transaction by transaction id.
     */
    public BenefitPaymentTransaction getTransaction(String transactionId) {

        return repository.findById(transactionId)
                .orElseThrow(() ->
                        new PaymentValidationException(
                                "Transaction not found : " + transactionId));
    }

    /**
     * Generates a unique transaction id.
     */
    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID();
    }
}