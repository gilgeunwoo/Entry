package com.example.entry.domain.submit.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SubmitAssignmentRequest {

    private String githubUrl;

    private String notionUrl;
}
