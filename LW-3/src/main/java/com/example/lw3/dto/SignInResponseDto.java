package com.example.lw3.dto;

import lombok.Data;

@Data
public class SignInResponseDto {
    private String token;

    public SignInResponseDto(String token) {
        this.token = token;
    }
}
