package com.derevetskyi.markiyan.adminmicroservice.service;

import com.derevetskyi.markiyan.adminmicroservice.entity.Product;
import com.derevetskyi.markiyan.adminmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
