package com.derevetskyi.markiyan.adminmicroservice.controller;

import com.derevetskyi.markiyan.adminmicroservice.entity.Product;
import com.derevetskyi.markiyan.adminmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }
}
