package com.example.lw3.mapper;

import com.example.lw3.dto.SignUpRequestDto;
import com.example.lw3.dto.UserDto;
import com.example.lw3.entity.Role;
import com.example.lw3.entity.User;

import java.util.HashSet;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toUser(SignUpRequestDto signUpRequestDto) {
        return User.builder()
            .firstName(signUpRequestDto.getFirstName())
            .lastName(signUpRequestDto.getLastName())
            .email(signUpRequestDto.getEmail())
            .password(signUpRequestDto.getPassword())
            .roles(new HashSet<>())
            .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
            .build();
    }
}
