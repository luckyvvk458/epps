package com.epps.epps.dto.response;

import com.epps.epps.enums.PaymentGatewayType;
import com.epps.epps.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BenefitPaymentResponse {

    private String transactionId;
    private String benefitId;
    private String employeeId;

    private BigDecimal amount;
    private String currency;

    private PaymentStatus status;

    // Gateway used for processing
    private PaymentGatewayType paymentGateway;

    // Reference returned by the gateway
    private String gatewayReferenceId;

    private String message;

    private LocalDateTime processedAt;

    public BenefitPaymentResponse() {
    }

    public BenefitPaymentResponse(String transactionId,
                                  String benefitId,
                                  String employeeId,
                                  BigDecimal amount,
                                  String currency,
                                  PaymentStatus status,
                                  PaymentGatewayType paymentGateway,
                                  String gatewayReferenceId,
                                  String message,
                                  LocalDateTime processedAt) {
        this.transactionId = transactionId;
        this.benefitId = benefitId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.paymentGateway = paymentGateway;
        this.gatewayReferenceId = gatewayReferenceId;
        this.message = message;
        this.processedAt = processedAt;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getBenefitId() {
        return benefitId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentGatewayType getPaymentGateway() {
        return paymentGateway;
    }

    public String getGatewayReferenceId() {
        return gatewayReferenceId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }
}
