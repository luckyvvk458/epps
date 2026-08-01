package com.epps.epps.dto.request;

import com.epps.epps.enums.PaymentGatewayType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Benefit payment request received after finance approval.
 */
public record BenefitPaymentRequest(

        @NotBlank(message = "Benefit Id is mandatory")
        String benefitId,

        @NotBlank(message = "Employee Id is mandatory")
        String employeeId,

        @NotNull(message = "Amount is mandatory")
        @DecimalMin(value = "0.01", message = "Amount should be greater than zero")
        BigDecimal amount,

        @NotBlank(message = "Currency is mandatory")
        String currency,

        @NotBlank(message = "Employee bank account is mandatory")
        String employeeBankAccount,

        @NotBlank(message = "IFSC Code is mandatory")
        String ifscCode,

        @NotNull(message = "Payment gateway is mandatory")
        PaymentGatewayType paymentGateway,

        boolean financeApproved

) {
}