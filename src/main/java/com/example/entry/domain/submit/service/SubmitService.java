package com.example.entry.domain.submit.service;

import com.example.entry.domain.submit.controller.dto.request.SubmitAssignmentRequest;
import com.example.entry.domain.submit.domain.Application;
import com.example.entry.domain.submit.domain.repository.ApplicationRepository;
import com.example.entry.domain.submit.exception.ApplicationNotFoundException;
import com.example.entry.domain.user.domain.User;
import com.example.entry.domain.user.domain.repository.UserRepository;
import com.example.entry.domain.auth.exception.UserNotFoundException;
import com.example.entry.global.email.MailService;
import com.example.entry.global.jwt.JwtTokenProvider;
import com.example.entry.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;


@RequiredArgsConstructor
@Service
public class SubmitService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ApplicationRepository applicationRepository;
    private final S3Service s3Service;
    private final MailService mailService;

    @Transactional
    public void submitAssignment(String token, SubmitAssignmentRequest submitAssignmentRequest) {
        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(token))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Application application = applicationRepository.findByUser(user)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
        application.submitAssignment(
                submitAssignmentRequest.getGithubUrl(),
                submitAssignmentRequest.getNotionUrl()
        );
    }

    @Transactional
    public String submitERD(String token, MultipartFile image) throws IOException {
        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(token))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Application application = applicationRepository.findByUser(user)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        String imageUrl = s3Service.upload(image);
        application.submitERD(imageUrl);

        return imageUrl;
    }

    public void apply(String token, String field) {
        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(token))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        applicationRepository.save(
                Application.builder()
                        .apply(true)
                        .field(field)
                        .user(user)
                        .username(user.getUsername())
                        .build()
        );
    }

    @Transactional
    public void applyBackEnd(String token) {
        apply(token, "back_end");
        mailService.simpleSend(jwtTokenProvider.getEmail(token),
                jwtTokenProvider.getUserPK(token) + "님은 Entry DSM 백엔드 분야에 지원하셨습니다.\n" +
                        "과제는 본 링크를 참고해주세요.\n" +
                        "https://www.notion.so/entry-9b4fa6263106459ab449616af51c3e87#bc7fa95f9c3041d48ecc5ecdf33d8430",
                jwtTokenProvider.getUserPK(token) + "님, Entry DSM 1학년 신입부원 모집지원이 정상적으로 완료되었습니다.");
    }

    @Transactional
    public void applyFrontEnd(String token) {
        apply(token, "front_end");
        mailService.simpleSend(jwtTokenProvider.getEmail(token),
                jwtTokenProvider.getUserPK(token) + "님은 Entry DSM 프론트엔드 분야에 지원하셨습니다." +
                        "과제는 본 링크를 참고해주세요." +
                        "프론트엔드 분야 과제 링크",
                jwtTokenProvider.getUserPK(token) + "님, Entry DSM 1학년 신입부원 모집지원이 정상적으로 완료되었습니다.");
    }

    @Transactional
    public void applyDesign(String token) {
        apply(token, "design");
        mailService.simpleSend(jwtTokenProvider.getEmail(token),
                jwtTokenProvider.getUserPK(token) + "님은 Entry DSM 디자인 분야에 지원하셨습니다." +
                        "과제는 본 링크를 참고해주세요." +
                        "디자인 분야 과제 링크",
                jwtTokenProvider.getUserPK(token) + "님, Entry DSM 1학년 신입부원 모집지원이 정상적으로 완료되었습니다.");
    }
}
