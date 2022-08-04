package com.example.entry.domain.user.controller;

import com.example.entry.domain.user.controller.dto.request.UserUpdateRequest;
import com.example.entry.domain.user.controller.dto.response.UserResponse;
import com.example.entry.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public void userUpdate(@RequestHeader("Authorization") String token, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(token, userUpdateRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public UserResponse getUser(@RequestHeader("Authorization") String token) {
        return userService.getUser(token);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String token) {
        userService.userDelete(token);
    }
}
