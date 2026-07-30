package com.epps.epps.controller;

import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@Import(ApiExceptionHandler.class)
class PaymentControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockitoBean private PaymentService paymentService;

    @Test
    void acceptsAValidPaymentRequest() throws Exception {
        given(paymentService.paymentResponse(any())).willReturn(new PaymentResponse("upi-123", "SUCCESS", "UPI payment processed successfully"));

        mockMvc.perform(post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":125.50,\"currency\":\"INR\",\"customerId\":\"customer-1\",\"merchantId\":\"merchant-1\",\"paymentMode\":\"UPI\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.paymentId").value("upi-123"))
            .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void rejectsARequestWithNoPaymentMode() throws Exception {
        mockMvc.perform(post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":125.50,\"currency\":\"INR\",\"customerId\":\"customer-1\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value("VALIDATION_ERROR"))
            .andExpect(jsonPath("$.fieldErrors.paymentMode").exists());
    }
}
