package com.epps.epps.processor;

import com.epps.epps.exception.PaymentGatewayException;
import com.epps.epps.enums.PaymentGatewayType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class BenefitGatewayRegistry {

    private final Map<PaymentGatewayType, BenefitPaymentGateway> gateways;

    public BenefitGatewayRegistry(List<BenefitPaymentGateway> gatewayList) {

        this.gateways = new EnumMap<>(PaymentGatewayType.class);

        for (BenefitPaymentGateway gateway : gatewayList) {
            gateways.put(gateway.getGatewayType(), gateway);
        }
    }

    public BenefitPaymentGateway resolve(PaymentGatewayType gatewayType) {

        BenefitPaymentGateway gateway = gateways.get(gatewayType);

        if (gateway == null) {
            throw new PaymentGatewayException(
                    "No payment gateway configured for : " + gatewayType);
        }

        return gateway;
    }
}