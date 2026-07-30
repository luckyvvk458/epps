package com.epps.epps.model;

import com.epps.epps.enums.PaymentMode;

import java.util.Objects;
import java.math.BigDecimal;

public class Payment {
    private final BigDecimal amount;
    private final String currency;
    private final String customerId;
    private final String merchantid;
    private final String description;
    private final String coupon;
    private final String callbackUrl;
    private final int retryCount;
    private final PaymentMode paymentMode;

    private Payment(Builder builder) {
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.customerId = builder.customerId;
        this.merchantid = builder.merchantid;
        this.description = builder.description;
        this.coupon = builder.coupon;
        this.callbackUrl = builder.callbackUrl;
        this.retryCount = builder.retryCount;
        this.paymentMode = builder.paymentMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return retryCount == payment.retryCount && Objects.equals(amount, payment.amount) && Objects.equals(currency, payment.currency) && Objects.equals(customerId, payment.customerId) && Objects.equals(merchantid, payment.merchantid) && Objects.equals(description, payment.description) && Objects.equals(coupon, payment.coupon) && Objects.equals(callbackUrl, payment.callbackUrl) && Objects.equals(paymentMode, payment.paymentMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency, customerId, merchantid, description, coupon, callbackUrl, retryCount, paymentMode);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", customerId='" + customerId + '\'' +
                ", merchantid='" + merchantid + '\'' +
                ", description='" + description + '\'' +
                ", coupon='" + coupon + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", retryCount=" + retryCount +
                ", paymentMode=" + paymentMode +
                '}';
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public String getDescription() {
        return description;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String customerId;
        private String merchantid;
        private String description;
        private String coupon;
        private String callbackUrl;
        private int retryCount;
        private PaymentMode paymentMode;

        private Builder(BigDecimal amount, String currency, String customerId) {
            this.amount = amount;
            this.currency = currency;
            this.customerId = customerId;
        }

        public Builder merchantId(String merchantid) {
            this.merchantid = merchantid;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder coupon(String coupon) {
            this.coupon = coupon;
            return this;
        }

        public Builder callbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        public Builder retryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public Builder paymentMode(PaymentMode paymentMode) {
            this.paymentMode = paymentMode;
            return this;
        }

        public Payment build() {
            if (amount == null || amount.signum() <= 0) {
                throw new IllegalStateException("amount cannot be zero or less");
            }
            if (currency == null || currency.isBlank()) {
                throw new IllegalStateException("Currency cannot be null or blank");
            }
            if (customerId == null || customerId.isBlank()) {
                throw new IllegalStateException("Customer Id cannot be null or blank ");
            }
            if (paymentMode == null) {
                throw new IllegalStateException("Payment mode cannot be null");
            }
            return new Payment(this);
        }
    }

    public static Builder builder(BigDecimal amount, String currency, String customerId) {
        return new Builder(amount, currency, customerId);
    }

}
