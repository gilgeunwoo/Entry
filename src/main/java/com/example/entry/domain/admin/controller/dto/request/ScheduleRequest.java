package com.example.entry.domain.admin.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequest {

    private Long month;

    private Long day;

    private String time;

    private String place;
}
