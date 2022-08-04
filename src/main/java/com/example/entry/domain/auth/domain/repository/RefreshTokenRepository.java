package com.example.entry.domain.auth.domain.repository;

import com.example.entry.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
