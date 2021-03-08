package com.example.lw3.controller;

import com.example.lw3.dto.SignInRequestDto;
import com.example.lw3.dto.SignInResponseDto;
import com.example.lw3.dto.SignUpRequestDto;
import com.example.lw3.dto.UserDto;
import com.example.lw3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.save(signUpRequestDto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.signIn(signInRequestDto));
    }
}
