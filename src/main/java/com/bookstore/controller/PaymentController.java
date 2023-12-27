package com.bookstore.controller;

import com.bookstore.model.entity.Payment;
import com.bookstore.model.enums.PaymentMethod;
import com.bookstore.model.enums.ResponseMessage;
import com.bookstore.model.request.PaymentRequest;
import com.bookstore.model.response.GenericResponse;
import com.bookstore.service.OrderService;
import com.bookstore.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Payment Endpoints", description = "Endpoints for mocking payment requests")
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;
    @PostMapping("/pay/{paymentMethod}")
    public ResponseEntity<GenericResponse> makePayment(@PathVariable PaymentMethod paymentMethod, @RequestBody PaymentRequest request){
        Set<String> requiredParams = paymentService.loadPaymentObject(paymentMethod);

        if(!requiredParams.containsAll((request.getPaymentObject().keySet())))
            return ResponseEntity.badRequest().body(
                    GenericResponse.builder()
                            .message("Request does not contain all required parameters")
                            .responseData(null)
                            .build());

        log.info("Making {} payment with: {}",paymentMethod ,request.getPaymentObject());
        String orderReference = request.getPaymentObject().get("orderReference");
        try {
            orderService.validateOrder(orderReference);
            return ResponseEntity.status(201).body(GenericResponse.builder()
                    .message(ResponseMessage.CREATED)
                    .responseData(paymentService.addPayment(paymentMethod, request))
                    .build());
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message("Order with reference: '" + orderReference + "' not found")
                    .responseData(null)
                    .build());
        }catch (Exception e) {
            log.warn("Failed to make payment due to: {}", e.toString());
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PatchMapping("/confirm/{paymentReference}")
    public ResponseEntity<GenericResponse> confirmPayment(@PathVariable String paymentReference){
        log.info("Confirming payment with reference: {}",paymentReference);
        try {
            Payment payment = paymentService.loadPaymentByReference(paymentReference);
            return ResponseEntity.accepted().body(GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(orderService.updatePaymentStatus(payment.getOrderReference()))
                    .build());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }catch (Exception e) {
            log.warn("Failed to make payment due to: {}", e.toString());
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

}
