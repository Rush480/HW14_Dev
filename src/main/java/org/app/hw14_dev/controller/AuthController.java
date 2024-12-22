package org.app.hw14_dev.controller;

import lombok.RequiredArgsConstructor;
import org.app.hw14_dev.model.dto.request.UserRequest;
import org.app.hw14_dev.service.SecurityService;
import org.app.hw14_dev.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;
    private final SecurityService securityService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody UserRequest userRequest) {
        return securityService.authenticateUser(userRequest);
    }
}
