package com.example.entry.domain.auth.service;


import com.example.entry.domain.auth.controller.dto.request.SignInRequest;
import com.example.entry.domain.auth.controller.dto.response.TokenResponse;
import com.example.entry.domain.auth.domain.types.Role;
import com.example.entry.domain.auth.exception.AlreadyExistUserException;
import com.example.entry.domain.user.domain.User;
import com.example.entry.domain.user.domain.repository.UserRepository;
import com.example.entry.domain.auth.controller.dto.request.SignUpRequest;
import com.example.entry.domain.auth.exception.InvalidPasswordException;
import com.example.entry.domain.auth.exception.UserNotFoundException;
import com.example.entry.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public void signup(SignUpRequest signUpRequest) {

        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw AlreadyExistUserException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build());
    }

    public TokenResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword()))
            throw InvalidPasswordException.EXCEPTION;

        return jwtTokenProvider.createTokens(user.getUsername(), user.getEmail());
    }

}
