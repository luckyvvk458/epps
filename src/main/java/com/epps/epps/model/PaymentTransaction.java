package com.epps.epps.model;

import com.epps.epps.enums.PaymentMode;
import com.epps.epps.enums.PaymentStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {
    @Id private UUID id;
    @Column(nullable = false, precision = 19, scale = 2) private BigDecimal amount;
    @Column(nullable = false, length = 3) private String currency;
    @Column(nullable = false) private String customerId;
    private String merchantId;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private PaymentMode paymentMode;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private PaymentStatus status;
    @Column(nullable = false) private OffsetDateTime createdAt;
    protected PaymentTransaction() { }
    public PaymentTransaction(Payment payment) {
        this.id = UUID.randomUUID(); this.amount = payment.getAmount(); this.currency = payment.getCurrency();
        this.customerId = payment.getCustomerId(); this.merchantId = payment.getMerchantid(); this.paymentMode = payment.getPaymentMode();
        this.status = PaymentStatus.PENDING; this.createdAt = OffsetDateTime.now();
    }
    public UUID getId() { return id; } public PaymentStatus getStatus() { return status; }
    public void markSuccessful() { this.status = PaymentStatus.SUCCESS; }
    public void markFailed() { this.status = PaymentStatus.FAILED; }
}
