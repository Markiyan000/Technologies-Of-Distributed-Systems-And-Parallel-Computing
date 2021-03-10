package com.example.lw3.service;

import com.example.lw3.dto.SignInRequestDto;
import com.example.lw3.dto.SignInResponseDto;
import com.example.lw3.dto.SignUpRequestDto;
import com.example.lw3.dto.UserDto;
import com.example.lw3.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto save(SignUpRequestDto signUpRequestDto);

    User findByEmail(String email);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    User getCurrentUser();
}
