package com.epps.epps.controller;

import com.epps.epps.dto.request.PaymentRequest;
import com.epps.epps.dto.response.PaymentResponse;
import com.epps.epps.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse paymentResponse(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.paymentResponse(paymentRequest);
    }
}
