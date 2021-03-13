package com.example.lw3.service.impl;

import com.example.lw3.dto.ProductDto;
import com.example.lw3.dto.ProductPostDto;
import com.example.lw3.entity.Product;
import com.example.lw3.exception.ProductNotFoundException;
import com.example.lw3.mapper.ProductMapper;
import com.example.lw3.repository.ProductRepository;
import com.example.lw3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Override
    public List<String> findAllCategories() {
        return productRepository.findAllCategories();
    }

    @Override
    public List<ProductDto> findByPrice(BigDecimal from, BigDecimal to) {
        List<Product> products = productRepository.findByPrice(from, to);

        return mapToProductDtoList(products);
    }

    @Override
    @Transactional
    public ProductDto save(ProductPostDto productPostDto) {
        Product newProduct = ProductMapper.toProduct(productPostDto);
        Product savedProduct = productRepository.save(newProduct);

        return ProductMapper.toProductDto(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto update(Long id, ProductPostDto productPostDto) {
        Product toUpdateProduct = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
        updateProduct(toUpdateProduct, productPostDto);
        Product updatedProduct = productRepository.save(toUpdateProduct);

        return ProductMapper.toProductDto(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream().map(ProductMapper::toProductDto).collect(Collectors.toList());
    }

    private void updateProduct(Product oldProduct, ProductPostDto newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setType(newProduct.getType());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setQuantity(newProduct.getQuantity());
        oldProduct.setCountry(newProduct.getCountry());
    }
}
