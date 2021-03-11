package com.example.lw3.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class OrderPostDto {
    private HashMap<Long, Integer> ids;
}
