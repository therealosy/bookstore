package com.bookstore.controller;

import com.bookstore.exception.InvalidVerificationCodeException;
import com.bookstore.model.request.AuthRequest;
import com.bookstore.model.request.RegisterRequest;
import com.bookstore.model.request.VerifyRequest;
import com.bookstore.model.response.RegisterResponse;
import com.bookstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
            ){
        try {
            return ResponseEntity.status(201).body(authService.register(request));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/verify-account")
    public ResponseEntity<Object> register(
            @RequestBody VerifyRequest request
    ){
        try {
            return ResponseEntity.ok(authService.verifyUser(request.getVerificationCode()));
        }catch (InvalidVerificationCodeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/token")
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthRequest request
    ){
        try {
            return ResponseEntity.accepted().body(authService.authenticate(request));
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
