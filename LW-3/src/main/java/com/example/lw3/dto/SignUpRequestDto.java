package com.example.lw3.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
