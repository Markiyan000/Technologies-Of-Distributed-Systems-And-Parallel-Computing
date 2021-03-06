package com.example.lw3.controller;

import com.example.lw3.dto.ProductDto;
import com.example.lw3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.findByName(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchByFilter(@RequestParam String filter) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.searchByFilter(filter));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> findAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.findAllCategories());
    }

    @GetMapping("/search-by-price")
    public ResponseEntity<List<ProductDto>> findByPrice(@RequestParam BigDecimal from, @RequestParam BigDecimal to) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.findByPrice(from, to));
    }

}
