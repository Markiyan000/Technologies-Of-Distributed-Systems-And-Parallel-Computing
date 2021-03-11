package com.example.lw3.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class OrderDto {
    private Long id;
    private LocalDateTime creationDate;
    private BigDecimal price;
    private Boolean isPaid;
    private UserDto userDto;
    private Set<ProductDto> productDtoS;
}
