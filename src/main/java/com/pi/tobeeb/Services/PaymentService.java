package com.pi.tobeeb.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final String STRIPE_SECRET_KEY = "sk_test_51MxfuEGPWonDGqDvQqpgUsd6tLBQBC9gcAhmpfhUeWutqDqDxOWvtM8VRaGPCsQIkMy2twnRQ2y4K1Ia8HKOuKmx00H3crhcF0";

    public Charge createCharge(Long amount, String currency, String description, String source) throws StripeException {
        Stripe.apiKey = STRIPE_SECRET_KEY;
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setDescription(description)
                .setSource(source)
                .build();
        Charge charge = Charge.create(params);
        return charge;
    }
}
