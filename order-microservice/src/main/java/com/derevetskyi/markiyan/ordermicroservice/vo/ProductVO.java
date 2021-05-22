package com.derevetskyi.markiyan.ordermicroservice.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {
    private Long id;

    private String name;

    private String type;

    private BigDecimal price;

    private Integer quantity;

    private String country;
}
