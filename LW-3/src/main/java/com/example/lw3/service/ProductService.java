package com.example.lw3.service;

import com.example.lw3.dto.ProductDto;
import com.example.lw3.dto.ProductPostDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    List<ProductDto> findByName(String name);

    List<ProductDto> searchByFilter(String filter);

    List<String> findAllCategories();

    List<ProductDto> findByPrice(BigDecimal from, BigDecimal to);

    ProductDto save(ProductPostDto productPostDto);

    ProductDto update(Long id, ProductPostDto productPostDto);

    void delete(Long id);
}
