package com.bookstore.service;

import com.bookstore.exception.InvalidPaymentMethodException;
import com.bookstore.model.entity.Payment;
import com.bookstore.model.enums.PaymentMethod;
import com.bookstore.model.enums.PaymentStatus;
import com.bookstore.model.request.PaymentRequest;
import com.bookstore.model.response.PaymentResponse;
import com.bookstore.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    public Set<String> loadPaymentObject(@NotNull PaymentMethod paymentMethod) throws InvalidPaymentMethodException{
        Set<String> requiredParams = null;

        switch (paymentMethod){
            case WEB ->
                requiredParams = Stream.of("pan", "cvv2", "pin", "expiry", "otp", "orderReference").collect(Collectors.toSet());
            case USSD ->
                requiredParams = Stream.of("ussdCode", "orderReference").collect(Collectors.toSet());
            case BANK_TRANSFER ->
                requiredParams = Stream.of("transfer", "orderReference").collect(Collectors.toSet());
        }

        if (requiredParams == null){
            throw new InvalidPaymentMethodException("Invalid Payment method supplied");
        }

        return requiredParams;
    }

    public Payment loadPaymentByReference(String paymentReference) {
        return paymentRepository.findByPaymentReference(paymentReference).orElseThrow();

    }

    public PaymentResponse addPayment(PaymentMethod paymentMethod,PaymentRequest request) {
        String paymentReference = UUID.randomUUID().toString();
        String orderReference = request.getPaymentObject().get("orderReference");


        Payment payment = paymentRepository.save(Payment.builder()
                .paymentReference(paymentReference)
                .orderReference(orderReference)
                .paymentMethod(paymentMethod)
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .build()
        );

        return PaymentResponse.builder().paymentReference(payment.getPaymentReference()).paymentStatus(payment.getPaymentStatus()).build();

    }
}
