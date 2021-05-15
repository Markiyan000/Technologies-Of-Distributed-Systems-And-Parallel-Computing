package com.derevetskyi.markiyan.productmicroservice.mapper;

import com.derevetskyi.markiyan.productmicroservice.dto.ProductDto;
import com.derevetskyi.markiyan.productmicroservice.dto.ProductPostDto;
import com.derevetskyi.markiyan.productmicroservice.entity.Product;

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

    public static Product toProduct(ProductPostDto productPostDto) {
        return Product.builder()
            .name(productPostDto.getName())
            .type(productPostDto.getType())
            .price(productPostDto.getPrice())
            .quantity(productPostDto.getQuantity())
            .country(productPostDto.getCountry())
            .build();
    }
}
