package com.example.lw3.service;

import com.example.lw3.dto.ProductDto;
import com.example.lw3.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> findAll();

}
