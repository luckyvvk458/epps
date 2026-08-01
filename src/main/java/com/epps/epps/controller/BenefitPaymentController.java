package com.epps.epps.controller;

import com.epps.epps.dto.request.BenefitPaymentRequest;
import com.epps.epps.dto.response.BenefitPaymentResponse;
import com.epps.epps.service.BenefitPaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/benefits")
public class BenefitPaymentController {

    private final BenefitPaymentService benefitPaymentService;

    public BenefitPaymentController(BenefitPaymentService benefitPaymentService) {
        this.benefitPaymentService = benefitPaymentService;
    }

    @PostMapping("/pay")
    public BenefitPaymentResponse pay(
            @RequestBody BenefitPaymentRequest request) {

        return benefitPaymentService.processBenefit(request);
    }
}
