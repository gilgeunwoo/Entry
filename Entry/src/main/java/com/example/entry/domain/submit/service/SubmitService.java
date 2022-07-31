package com.example.entry.domain.submit.service;

import com.example.entry.domain.submit.domain.Application;
import com.example.entry.domain.submit.domain.repository.ApplicationRepository;
import com.example.entry.domain.submit.exception.ApplicationNotFoundException;
import com.example.entry.domain.user.domain.User;
import com.example.entry.domain.user.domain.repository.UserRepository;
import com.example.entry.domain.user.exception.UserNotFoundException;
import com.example.entry.global.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SubmitService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final ApplicationRepository applicationRepository;

    public void apply(String token) {
        Claims claims = jwtTokenProvider.parseToken(token);
        User user = userRepository.findByEmail(claims.get("email", String.class))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Application application = applicationRepository.findByUser(user)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        application.setApply(!(application.isApply()));
        applicationRepository.save(application);


    }
}
