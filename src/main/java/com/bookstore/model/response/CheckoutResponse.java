package com.bookstore.model.response;

import com.bookstore.model.enums.PaymentMethod;
import com.bookstore.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {
    private PaymentMethod paymentMethod;
    private Set<String> paymentObject;
    private String orderReference;
    private PaymentStatus paymentStatus;
    private Long totalAmount;
}
