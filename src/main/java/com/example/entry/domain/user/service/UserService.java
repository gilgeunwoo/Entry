package com.example.entry.domain.user.service;

import com.example.entry.domain.auth.exception.UserNotFoundException;
import com.example.entry.domain.submit.domain.Application;
import com.example.entry.domain.submit.domain.repository.ApplicationRepository;
import com.example.entry.domain.submit.exception.ApplicationNotFoundException;
import com.example.entry.domain.user.controller.dto.request.UserUpdateRequest;
import com.example.entry.domain.user.controller.dto.response.UserResponse;
import com.example.entry.domain.user.domain.User;
import com.example.entry.domain.user.domain.repository.UserRepository;
import com.example.entry.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationRepository applicationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void userDelete(String token) {
        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(token))
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        userRepository.delete(user);
        Application application = applicationRepository.findByUser(user)
                        .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
        applicationRepository.delete(application);
    }

    @Transactional
    public void updateUser(String token, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(token))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        user.update(userUpdateRequest.getUsername(),
                    userUpdateRequest.getEmail(),
                    passwordEncoder.encode(userUpdateRequest.getPassword()));
    }

    public UserResponse getUser(String token) {
        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(token))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Application application = applicationRepository.findByUser(user)
                .map(app -> Application.builder()
                        .id(app.getId())
                        .apply(app.isApply())
                        .field(app.getField())
                        .username(app.getUsername())
                        .githubUrl(app.getGithubUrl())
                        .notionUrl(app.getNotionUrl())
                        .build())
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .application(application)
                .build();
    }
}
