package com.bookstore.service;

import com.bookstore.model.entity.User;
import com.bookstore.model.entity.Verification;
import com.bookstore.model.request.AuthRequest;
import com.bookstore.model.request.RegisterRequest;
import com.bookstore.model.response.AuthResponse;
import com.bookstore.model.response.RegisterResponse;
import com.bookstore.model.response.UpdateResponse;
import com.bookstore.util.auth.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final VerificationService verificationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public RegisterResponse register(@NotNull RegisterRequest request){
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);
        User user = userService.addUser(request);
        Verification verification = verificationService.addVerification(user.getUserId());

        return RegisterResponse.builder()
              .message("Success")
              .verificationCode(verification.getVerificationCode())
              .expiresIn(verification.getExpiresAt())
              .build();
    }

    public AuthResponse authenticate(@NotNull AuthRequest request){
        log.info("Authenticating user {}", request.getEmail());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.loadUserByUsername(request.getEmail());

        return  AuthResponse.builder()
                .token(jwtUtil.generateToken(user))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public UpdateResponse verifyUser(String verificationCode) throws Exception{

        Verification verification = verificationService.loadVerificationCode(verificationCode);
        userService.enableUser(verification.getUserId());

        verificationService.deleteVerificationCode(verification.getUserId());
        return UpdateResponse.builder().message("Successfully verified User").build();
    }
}
