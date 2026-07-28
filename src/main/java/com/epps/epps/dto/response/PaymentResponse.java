package com.epps.epps.dto.response;

public class PaymentResponse {
    private String paymentId;
    private String status;
    private String message;

    public PaymentResponse(String paymentId, String status, String message) {
        this.paymentId = paymentId;
        this.status = status;
        this.message = message;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
