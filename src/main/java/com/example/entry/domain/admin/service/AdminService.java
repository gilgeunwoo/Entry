package com.example.entry.domain.admin.service;

import com.example.entry.domain.admin.controller.dto.request.ScheduleRequest;
import com.example.entry.domain.auth.exception.UserNotFoundException;
import com.example.entry.domain.submit.domain.Application;
import com.example.entry.domain.submit.domain.repository.ApplicationRepository;
import com.example.entry.domain.submit.exception.ApplicationNotFoundException;
import com.example.entry.domain.user.domain.User;
import com.example.entry.domain.user.domain.repository.UserRepository;
import com.example.entry.global.email.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    public List<Application> getApplicationList() {
        return applicationRepository.findAll();
    }

    public void sendScheduleEmail(ScheduleRequest scheduleRequest, String username) {
        User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        mailService.simpleSend(user.getEmail(),
                "안녕하세요, " + user.getUsername() + "님! 면접 일정이 정해져 안내드립니다.\n" +
                        "면접일정은 " +
                        scheduleRequest.getMonth() + "월" +
                        scheduleRequest.getDay() + "일 " +
                        scheduleRequest.getTime() + "," +
                        scheduleRequest.getPlace() + "에서 진행됩니다.\n" +
                        "늦지않게 잘 도착해주시길 바랍니다.",
                "Entry DSM에서 " +user.getUsername() + "님의 면접일정을 안내드립니다."
        );
    }

    public void sendPassEmail(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Application application = applicationRepository.findByUser(user)
                        .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
        mailService.simpleSend(user.getEmail(),
                user.getUsername() + "님 축하드립니다.\n" +
                        user.getUsername() + "님은 Entry DSM 1학년 신입부원 모집에서 통과하셨습니다!",
                "Entry DSM에서 " + user.getUsername() + "님의 면접 결과를 발표합니다."
        );
        application.setPass(true);
        applicationRepository.save(application);
    }

    public void sendFailEmail(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Application application = applicationRepository.findByUser(user)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
        mailService.simpleSend(user.getEmail(),
                user.getUsername() + "님, 안타깝지만 " +
                        user.getUsername() + "님은 이번 Entry DSM 1학년 신입부원 모집에서 떨어지셨습니다. ",
                user.getUsername() + "님의 면접 결과를 발표합니다."
        );
        application.setPass(true);
        applicationRepository.save(application);
    }
}
