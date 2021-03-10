package com.example.lw3.mapper;

import com.example.lw3.dto.ProductDto;
import com.example.lw3.entity.Product;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .type(product.getType())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .country(product.getCountry())
            .build();
    }
}
