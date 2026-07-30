package com.epps.epps.processor;

import com.epps.epps.enums.PaymentMode;
import org.springframework.stereotype.Component;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/** Strategy registry: adding a payment mode needs a processor, not a service conditional. */
@Component
public class PaymentProcessorRegistry {
    private final Map<PaymentMode, PaymentProcessor> processors = new EnumMap<>(PaymentMode.class);

    public PaymentProcessorRegistry(List<PaymentProcessor> availableProcessors) {
        availableProcessors.forEach(processor -> processors.put(processor.supports(), processor));
    }

    public PaymentProcessor forMode(PaymentMode mode) {
        PaymentProcessor processor = processors.get(mode);
        if (processor == null) throw new IllegalArgumentException("Unsupported payment mode: " + mode);
        return processor;
    }
}
