package com.example.lw3.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPostDto {
    private String name;
    private String type;
    private BigDecimal price;
    private Integer quantity;
    private String country;
}
