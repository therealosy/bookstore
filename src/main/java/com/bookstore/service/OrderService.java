package com.bookstore.service;

import com.bookstore.model.entity.Book;
import com.bookstore.model.entity.Order;
import com.bookstore.model.entity.User;
import com.bookstore.model.enums.PaymentMethod;
import com.bookstore.model.enums.PaymentStatus;
import com.bookstore.model.response.CheckoutResponse;
import com.bookstore.model.response.UpdateResponse;
import com.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;
    private final PaymentService paymentService;

    public CheckoutResponse addOrder(Long userId, PaymentMethod paymentMethod){
        List<Book> books = new ArrayList<>(cartService.loadCartByUserId(userId).getBooks());
        User user = userService.loadUserById(userId);
        Long total = books.stream().mapToLong(Book::getPrice).sum();
        String orderReference = UUID.randomUUID().toString();

        orderRepository.save(
                Order.builder()
                .user(user)
                .books(books)
                .paymentMethod(paymentMethod)
                .total(total)
                .hasPaid(false)
                .orderReference(orderReference)
                .build()
        );

        return CheckoutResponse.builder()
                .paymentMethod(paymentMethod)
                .paymentObject(paymentService.loadPaymentObject(paymentMethod))
                .orderReference(orderReference)
                .totalAmount(total)
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

    public UpdateResponse updatePaymentStatus(String orderReference){
        Order order = orderRepository.findByOrderReference(orderReference).orElseThrow();
        order.setHasPaid(true);
        orderRepository.save(order);

        return UpdateResponse.builder().message("Payment Successful").build();
    }

    public Order loadOrderByReference(String orderReference){
        log.info("Finding order with reference: {}", orderReference);

        Order order = orderRepository.findByOrderReference(orderReference).orElseThrow();

        log.info("Found Order {}", order);

        return  order;
    }

    public void validateOrder(String orderReference) throws NoSuchElementException{
        loadOrderByReference(orderReference);
    }
}
