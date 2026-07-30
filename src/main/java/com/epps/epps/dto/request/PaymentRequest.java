package com.epps.epps.dto.request;

import com.epps.epps.enums.PaymentMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PaymentRequest {
    @NotNull @DecimalMin(value = "0.01") private BigDecimal amount;
    @NotBlank private String currency;
    @NotBlank private String customerId;

    private String merchantId;
    private String coupon;
    private String description;
    private String callbackUrl;
    private int retryCount;
    @NotNull private PaymentMode paymentMode;

    public PaymentRequest() { }

    public PaymentRequest(BigDecimal amount, String currency, String customerId, String merchantId, String coupon, String description, String callbackUrl, int retryCount, PaymentMode paymentMode) {
        this.amount = amount;
        this.currency = currency;
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.coupon = coupon;
        this.description = description;
        this.callbackUrl = callbackUrl;
        this.retryCount = retryCount;
        this.paymentMode = paymentMode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
