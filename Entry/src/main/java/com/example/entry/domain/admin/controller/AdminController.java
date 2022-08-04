package com.example.entry.domain.admin.controller;

import com.example.entry.domain.admin.controller.dto.request.ScheduleRequest;
import com.example.entry.domain.admin.service.AdminService;
import com.example.entry.domain.submit.domain.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/apps")
    public List<Application> getApplicationList() {
        return adminService.getApplicationList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/schedule/{username}")
    public void sendSchedule(@RequestBody ScheduleRequest scheduleRequest, @PathVariable String username) {
        adminService.sendScheduleEmail(scheduleRequest, username);
    }


}
