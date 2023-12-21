package com.bookstore.controller;

import com.bookstore.exception.InvalidVerificationCodeException;
import com.bookstore.model.enums.ResponseMessage;
import com.bookstore.model.request.AuthRequest;
import com.bookstore.model.request.RegisterRequest;
import com.bookstore.model.response.GenericResponse;
import com.bookstore.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Endpoints", description = "Endpoints for registering, verifying and authenticating users")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(
            @RequestBody RegisterRequest request
            ){
        log.info("Registering user: {}", request.getEmail());
        try {
            return ResponseEntity.status(201).body(GenericResponse.builder()
                    .message(ResponseMessage.CREATED)
                    .responseData(authService.register(request))
                    .build());
        }catch (Exception e) {
            log.warn("Failed to register user due to: {}", e.toString());
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PatchMapping("/verify-account/{verificationCode}")
    public ResponseEntity<GenericResponse> verifyUser(
            @PathVariable String verificationCode
    ){
        try {
            return ResponseEntity.accepted().body(GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(authService.verifyUser(verificationCode))
                    .build());
        }catch (InvalidVerificationCodeException e){
            return ResponseEntity.badRequest().body(GenericResponse.builder()
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

    @PostMapping("/token")
    public ResponseEntity<GenericResponse> authenticate(
            @RequestBody AuthRequest request
    ){
        try {
            return ResponseEntity.ok(GenericResponse.builder()
                    .message(ResponseMessage.SUCCESS)
                    .responseData(authService.authenticate(request))
                    .build());
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
        catch (Exception e) {
            log.warn("Failed to authenticate user due to {}", e.toString());
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }
}
