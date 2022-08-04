package com.example.entry.domain.auth.service;

import com.example.entry.domain.admin.domain.Admin;
import com.example.entry.domain.admin.domain.repository.AdminRepository;
import com.example.entry.domain.auth.controller.dto.request.AdminAuthRequest;
import com.example.entry.domain.auth.controller.dto.response.TokenResponse;
import com.example.entry.domain.auth.domain.types.Role;
import com.example.entry.domain.auth.exception.AlreadyExistUserException;
import com.example.entry.domain.auth.exception.InvalidPasswordException;
import com.example.entry.domain.auth.exception.UserNotFoundException;
import com.example.entry.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthService {

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public void adminSignUp(AdminAuthRequest adminAuthRequest) {

        if (adminRepository.findByAdminId(adminAuthRequest.getAdminId()).isPresent()) {
            throw AlreadyExistUserException.EXCEPTION;
        }

        adminRepository.save(
                Admin.builder()
                        .adminId(adminAuthRequest.getAdminId())
                        .password(passwordEncoder.encode(adminAuthRequest.getPassword()))
                        .role(Role.ROLE_ADMIN)
                        .build());
    }

    public TokenResponse adminSignIn(AdminAuthRequest adminAuthRequest) {
        Admin admin = adminRepository.findByAdminId(adminAuthRequest.getAdminId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        if (!passwordEncoder.matches(adminAuthRequest.getPassword(), admin.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }
        return jwtTokenProvider.createAdminTokens(admin.getAdminId());
    }
}
