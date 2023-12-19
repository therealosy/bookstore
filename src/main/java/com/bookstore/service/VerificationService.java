package com.bookstore.service;

import com.bookstore.exception.InvalidVerificationCodeException;
import com.bookstore.model.entity.Verification;
import com.bookstore.model.response.UpdateResponse;
import com.bookstore.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationService {

    @Value("${bookstore.jwt.validity-duration-seconds: 900}")
    private static Integer VALIDITY_DURATION_SECONDS;

    private final VerificationRepository verificationRepository;
    public String generateVerificationToken(){
        return UUID.randomUUID().toString();
    }

    public Verification addVerification(Long userId){
        Date expiresIn = new Date(System.currentTimeMillis() + (VALIDITY_DURATION_SECONDS * 1000));

        return verificationRepository.save(Verification.builder()
                .userId(userId)
                .verificationCode(generateVerificationToken())
                .expires(expiresIn).build()
        );
    }

    public Verification loadVerificationCode(String verificationCode) throws InvalidVerificationCodeException {
        Verification verification = verificationRepository.findByVerificationCode(verificationCode).orElse(null);

        if (verification == null)
            throw  new InvalidVerificationCodeException("Invalid Verification Token");

        if (!(verification.getExpires().before(new Date()))){
            throw  new InvalidVerificationCodeException("Verification Token has Expired");
        }

        return verification;
    }

    public void deleteVerificationCode(String verificationCode) throws InvalidVerificationCodeException{
        verificationRepository.deleteByVerificationCode(verificationCode);
    }
}
