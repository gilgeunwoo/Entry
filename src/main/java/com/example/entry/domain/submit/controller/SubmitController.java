package com.example.entry.domain.submit.controller;

import com.example.entry.domain.submit.controller.dto.request.SubmitAssignmentRequest;
import com.example.entry.domain.submit.service.SubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/submit")
public class SubmitController {

    private final SubmitService submitService;


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/apply/back")
    public void applyBackEnd(@RequestHeader("Authorization") String token) {
        submitService.applyBackEnd(token);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/apply/front")
    public void applyFrontEnd(@RequestHeader("Authorization") String token) {
        submitService.applyFrontEnd(token);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/apply/design")
    public void applyDesign(@RequestHeader("Authorization") String token) {
        submitService.applyDesign(token);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/assignment")
    public void submitAssignment(@RequestHeader("Authorization") String token, @RequestBody SubmitAssignmentRequest submitAssignmentRequest) {
        submitService.submitAssignment(token, submitAssignmentRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/ERD")
    public String submitERD(@RequestHeader("Authorization") String token, @RequestPart("ERD")MultipartFile image) throws IOException {
        return submitService.submitERD(token, image);
    }


}
