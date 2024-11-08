package com.hampcode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hampcode.model.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    Optional<PasswordResetToken> findByToken(String token);
}
