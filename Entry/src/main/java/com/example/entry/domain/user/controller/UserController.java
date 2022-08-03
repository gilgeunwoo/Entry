package com.example.entry.domain.user.controller;

import com.example.entry.domain.user.controller.dto.request.UserUpdateRequest;
import com.example.entry.domain.user.controller.dto.response.UserResponse;
import com.example.entry.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PutMapping
    public void userUpdate(@RequestHeader("Authorization") String token, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(token, userUpdateRequest);
    }

    @GetMapping
    public UserResponse getUser(@RequestHeader("Authorization") String token) {
        return userService.getUser(token);
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String token) {
        userService.userDelete(token);
    }
}
