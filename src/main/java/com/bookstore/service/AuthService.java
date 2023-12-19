package com.bookstore.service;

import com.bookstore.model.entity.User;
import com.bookstore.model.entity.Verification;
import com.bookstore.model.request.AuthRequest;
import com.bookstore.model.request.RegisterRequest;
import com.bookstore.model.response.AuthResponse;
import com.bookstore.model.response.RegisterResponse;
import com.bookstore.model.response.UpdateResponse;
import com.bookstore.repository.UserRepository;
import com.bookstore.repository.VerificationRepository;
import com.bookstore.util.auth.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final VerificationService verificationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final VerificationRepository verificationRepository;

    public RegisterResponse register(RegisterRequest request){
      User user = userService.addUser(request);
      Verification verification = verificationService.addVerification(user.getUserId());

      return RegisterResponse.builder()
              .message("Success")
              .verificationCode(verification.getVerificationCode())
              .expiresIn(verification.getExpires())
              .build();
    }

    public AuthResponse authenticate(AuthRequest request){
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

        verificationService.deleteVerificationCode(verificationCode);
        return UpdateResponse.builder().message("Successfully verified User").build();
    }
}
