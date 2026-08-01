package com.epps.epps.controller;

import com.epps.epps.config.SecurityConfig;
import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BenefitPaymentController.class)
@Import({SecurityConfig.class, ApiExceptionHandler.class})
class BenefitPaymentControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockitoBean private PaymentService paymentService;
    private static final String BODY = "{\"benefitReference\":\"BEN-1\",\"beneficiaryId\":\"employee-1\",\"merchantId\":\"merchant-1\",\"amount\":125.50,\"currency\":\"INR\",\"paymentMode\":\"UPI\",\"approvalReference\":\"APP-1\"}";

    @Test
    void acceptsAnAuthorizedBenefitsPayment() throws Exception {
        given(paymentService.paymentResponse(any())).willReturn(new PaymentResponse("upi-123", "SUCCESS", "Payment processed successfully"));
        mockMvc.perform(post("/benefit-payments").contentType("application/json").content(BODY)
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_benefits.payments.write"))))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.paymentId").value("upi-123"));
    }

    @Test
    void rejectsATokenWithoutTheBenefitsScope() throws Exception {
        mockMvc.perform(post("/benefit-payments").contentType("application/json").content(BODY).with(jwt()))
            .andExpect(status().isForbidden());
    }
}
