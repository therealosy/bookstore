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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/user/me")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User Endpoints", description = "Endpoints for users to manage their accounts including their carts and orders")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/cart")
    public ResponseEntity<GenericResponse> getUserCart(Authentication authentication){
        String userName = authentication.getName();
        log.info("Fetching cart for, {}", userName);
        try {
            Long userId = userService.getUserIdFromAuthentication(authentication);
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

    @GetMapping("/orders")
    public ResponseEntity<GenericResponse> getUserOrders(Authentication authentication){
        String userName = authentication.getName();
        log.info("Fetching Orders for, {}", userName);
        try {
            Long userId = userService.getUserIdFromAuthentication(authentication);
            return ResponseEntity.ok(
                    GenericResponse.builder()
                            .message(ResponseMessage.SUCCESS)
                            .responseData(userService.loadUserOrders(userId))
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


    @PutMapping("/cart/{bookId}")
    public ResponseEntity<GenericResponse> addToUserCart(@PathVariable Long bookId, Authentication authentication){
        String userName = authentication.getName();
        log.info("Adding Book to cart for, {}", userName);
        try {
            Long userId = userService.getUserIdFromAuthentication(authentication);
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

    @DeleteMapping("/cart/{bookId}")
    public ResponseEntity<GenericResponse> removeFromCart(@PathVariable Long bookId, Authentication authentication){
        String userName = authentication.getName();
        log.info("Removing book from cart for, {}", userName);
        try {
            Long userId = userService.getUserIdFromAuthentication(authentication);
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

    @PostMapping("/cart/checkout")
    public ResponseEntity<GenericResponse> checkoutUserCart(@RequestParam PaymentMethod paymentMethod, Authentication authentication){
        String userName = authentication.getName();
        log.info("Checking out cart for, {}", userName);
        try {
            Long userId = userService.getUserIdFromAuthentication(authentication);
            CheckoutResponse checkoutOrder = orderService.addOrder(userId, paymentMethod);
            cartService.emptyCart(userId);
            return ResponseEntity.status(201).body(GenericResponse.builder()
                    .message(ResponseMessage.CREATED)
                    .responseData(checkoutOrder)
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PatchMapping("/password")
    public ResponseEntity<GenericResponse> addToUserCart(@RequestBody UpdatePasswordRequest request, Authentication authentication){
        String userName = authentication.getName();
        log.info("Updating password for, {}", userName);
        try {
            String encodedPassword = passwordEncoder.encode(request.getNewPassword());
            return ResponseEntity.accepted().body(GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(userService.updateUserPassword(userName, encodedPassword))
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

    @GetMapping("/")
    public ResponseEntity<GenericResponse> getUser(Authentication authentication){
        String userName = authentication.getName();
        log.info("Fetching user: {}", userName);
        try {
            return ResponseEntity.ok(
                    GenericResponse.builder()
                            .message(ResponseMessage.SUCCESS)
                            .responseData(userService.loadUserByUsername(userName))
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
