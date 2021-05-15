package com.derevetskyi.markiyan.productmicroservice.service.impl;

import com.derevetskyi.markiyan.productmicroservice.dto.ProductDto;
import com.derevetskyi.markiyan.productmicroservice.dto.ProductPostDto;
import com.derevetskyi.markiyan.productmicroservice.entity.Product;
import com.derevetskyi.markiyan.productmicroservice.exception.ProductNotFoundException;
import com.derevetskyi.markiyan.productmicroservice.mapper.ProductMapper;
import com.derevetskyi.markiyan.productmicroservice.message.Messages;
import com.derevetskyi.markiyan.productmicroservice.repository.ProductRepository;
import com.derevetskyi.markiyan.productmicroservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
            .orElseThrow(() -> new ProductNotFoundException(Messages.PRODUCT_NOT_FOUND + id));

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
            .orElseThrow(() -> new ProductNotFoundException(Messages.PRODUCT_NOT_FOUND + id));
        updateProduct(toUpdateProduct, productPostDto);
        Product updatedProduct = productRepository.save(toUpdateProduct);

        return ProductMapper.toProductDto(updatedProduct);
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
