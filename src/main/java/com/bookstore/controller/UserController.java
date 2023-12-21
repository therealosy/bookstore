package com.bookstore.controller;



import com.bookstore.model.enums.PaymentMethod;
import com.bookstore.model.enums.ResponseMessage;
import com.bookstore.model.request.UpdatePasswordRequest;
import com.bookstore.model.response.CheckoutResponse;
import com.bookstore.model.response.GenericResponse;
import com.bookstore.service.CartService;
import com.bookstore.service.OrderService;
import com.bookstore.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User Endpoints", description = "Endpoints for users to manage their accounts including their carts and orders")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{userId}/cart")
    public ResponseEntity<GenericResponse> getUserCart(@PathVariable Long userId){
        try {
            return ResponseEntity.ok(
                    GenericResponse.builder()
                            .message(ResponseMessage.SUCCESS)
                            .responseData(cartService.loadCartByUserId(userId))
                            .build()
                    );
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PostMapping("/{userId}/cart")
    public ResponseEntity<GenericResponse> createUserCart(@PathVariable Long userId){
        try {
            return ResponseEntity.status(201).body(GenericResponse.builder()
                    .message(ResponseMessage.CREATED)
                    .responseData(cartService.createCart(userId))
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }


    @PutMapping("/{userId}/cart/{bookId}")
    public ResponseEntity<GenericResponse> addToUserCart(@PathVariable Long userId, @PathVariable Long bookId){
        try {
            return ResponseEntity.accepted().body(GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(cartService.addBookToCart(userId, bookId))
                    .build());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @DeleteMapping("/{userId}/cart/{bookId}")
    public ResponseEntity<GenericResponse> removeFromCart(@PathVariable Long userId, @PathVariable Long bookId){
        try {
            return ResponseEntity.accepted().body(GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(cartService.removeBookFromCart(userId, bookId))
                    .build());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PostMapping("/{userId}/cart/checkout")
    public ResponseEntity<GenericResponse> checkoutUserCart(@PathVariable Long userId, @RequestBody PaymentMethod paymentMethod){
        try {
            CheckoutResponse checkoutResponse = orderService.addOrder(userId, paymentMethod);
            cartService.emptyCart(userId);
            return ResponseEntity.status(201).body(GenericResponse.builder()
                    .message(ResponseMessage.CREATED)
                    .responseData(checkoutResponse)
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<GenericResponse> addToUserCart(@PathVariable Long userId, @RequestBody UpdatePasswordRequest request){
        try {
            String encodedPassword = passwordEncoder.encode(request.getNewPassword());
            return ResponseEntity.accepted().body(GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(userService.updateUserPassword(userId, encodedPassword))
                    .build());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GenericResponse> getUser(@PathVariable Long userId){
        try {
            return ResponseEntity.ok(
                    GenericResponse.builder()
                            .message(ResponseMessage.SUCCESS)
                            .responseData(userService.loadUserById(userId))
                            .build());
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(404).body(
                    GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build()
            );
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

}
