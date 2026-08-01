package com.epps.epps.service;

import com.epps.epps.dto.request.BenefitPaymentRequest;
import com.epps.epps.exception.PaymentValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BenefitValidator {

    public void validate(BenefitPaymentRequest request) {

        validateRequest(request);
        validateBusinessRules(request);
    }

    /**
     * Basic request validations.
     */
    private void validateRequest(BenefitPaymentRequest request) {

        if (request == null) {
            throw new PaymentValidationException(
                    "Benefit payment request cannot be null.");
        }

        requireNonBlank(request.employeeId(), "Employee Id is mandatory.");
        requireNonBlank(request.benefitId(), "Benefit Id is mandatory.");
        requireNonBlank(request.currency(), "Currency is mandatory.");
        requireNonBlank(request.employeeBankAccount(), "Employee bank account is mandatory.");
        requireNonBlank(request.ifscCode(), "IFSC Code is mandatory.");

        validateAmount(request.amount());

        if (request.paymentGateway() == null) {
            throw new PaymentValidationException(
                    "Payment Gateway is mandatory.");
        }
    }

    /**
     * Business validations.
     */
    private void validateBusinessRules(BenefitPaymentRequest request) {

        if (!request.financeApproved()) {
            throw new PaymentValidationException(
                    "Benefit payment is not approved by Finance.");
        }

        // Future database validations:
        // validateEmployeeExists(request.employeeId());
        // validateBenefitExists(request.benefitId());
        // validateDuplicatePayment(request.benefitId());
    }

    private void validateAmount(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentValidationException(
                    "Benefit amount should be greater than zero.");
        }
    }

    private void requireNonBlank(String value, String message) {

        if (value == null || value.isBlank()) {
            throw new PaymentValidationException(message);
        }
    }
}