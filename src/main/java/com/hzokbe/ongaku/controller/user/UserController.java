package com.hzokbe.ongaku.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.ongaku.model.jwt.response.JwtResponse;
import com.hzokbe.ongaku.model.user.request.UserRequest;
import com.hzokbe.ongaku.model.user.response.UserResponse;
import com.hzokbe.ongaku.service.user.UserService;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(service.signIn(authentication));
    }
}
