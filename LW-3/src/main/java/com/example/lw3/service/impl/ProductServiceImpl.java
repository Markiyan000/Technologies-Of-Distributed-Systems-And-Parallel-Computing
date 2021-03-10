package com.example.lw3.service.impl;

import com.example.lw3.dto.ProductDto;
import com.example.lw3.entity.Product;
import com.example.lw3.mapper.ProductMapper;
import com.example.lw3.repository.ProductRepository;
import com.example.lw3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductMapper::toProductDto).collect(Collectors.toList());
    }
}
