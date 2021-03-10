package com.example.lw3.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String type;
    private BigDecimal price;
    private Integer quantity;
    private String country;
}
