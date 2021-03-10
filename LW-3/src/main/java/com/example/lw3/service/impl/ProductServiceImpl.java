package com.example.lw3.service.impl;

import com.example.lw3.dto.ProductDto;
import com.example.lw3.entity.Product;
import com.example.lw3.exception.ProductNotFoundException;
import com.example.lw3.mapper.ProductMapper;
import com.example.lw3.repository.ProductRepository;
import com.example.lw3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.lw3.messages.Messages.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return mapToProductDtoList(products);
    }

    @Override
    public ProductDto findById(Long id) {
        Product foundProduct = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));

        return ProductMapper.toProductDto(foundProduct);
    }

    @Override
    public List<ProductDto> findByName(String name) {
        List<Product> products = productRepository.findByName(name);

        return mapToProductDtoList(products);
    }

    @Override
    public List<ProductDto> searchByFilter(String filter) {
        List<Product> products = productRepository.searchByFilter(filter);

        return mapToProductDtoList(products);
    }

    private List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream().map(ProductMapper::toProductDto).collect(Collectors.toList());
    }
}
