package com.sparta.schedulemanagement.user.controller;

import com.sparta.schedulemanagement.user.dto.SignupRequestDto;
import com.sparta.schedulemanagement.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignupRequestDto requestDto) {

        return userService.signup(requestDto);
    }

    @GetMapping("/token/reissue")
    public ResponseEntity<String> reissueToken(HttpServletRequest request, HttpServletResponse response) {

        return userService.reissueToken(request, response);
    }

}
