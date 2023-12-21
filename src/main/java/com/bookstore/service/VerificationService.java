package com.bookstore.service;

import com.bookstore.exception.InvalidVerificationCodeException;
import com.bookstore.model.entity.Verification;
import com.bookstore.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationService {

    @Value("${bookstore.auth.verification-code.validity-duration-seconds}")
    private Integer VALIDITY_DURATION_SECONDS;

    private final VerificationRepository verificationRepository;
    public String generateVerificationToken(){
        return UUID.randomUUID().toString();
    }

    public Verification addVerification(Long userId){
        Date expiresAt = new Date(System.currentTimeMillis() + (VALIDITY_DURATION_SECONDS * 1000));

        log.info("Creating verification code expires at {}", expiresAt);

        return verificationRepository.save(Verification.builder()
                .userId(userId)
                .verificationCode(generateVerificationToken())
                .expiresAt(expiresAt).build()
        );
    }

    public Verification loadVerificationCode(String verificationCode) throws InvalidVerificationCodeException {
        log.info("Getting verification code: {} from DB", verificationCode);

        Verification verification = verificationRepository.findByVerificationCode(verificationCode).orElse(null);

        if (verification == null) {
            log.warn("Verification code not found");
            throw new InvalidVerificationCodeException("Invalid Verification Token");
        }

        if (new Date().after(verification.getExpiresAt())){
            log.warn("Verification Expired at {}.", verification.getExpiresAt().toString());
            throw  new InvalidVerificationCodeException("Verification Token has Expired");
        }

        log.info("Found Verification Code");
        return verification;
    }

    public void deleteVerificationCode(Long userId){
        verificationRepository.deleteById(userId);
    }
}
