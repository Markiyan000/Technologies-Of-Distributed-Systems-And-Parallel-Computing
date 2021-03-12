package com.example.lw3.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BlacklistDto {
    private Long id;
    private LocalDateTime creationDate;
    private BigDecimal debt;
    private UserDto userDto;
}
