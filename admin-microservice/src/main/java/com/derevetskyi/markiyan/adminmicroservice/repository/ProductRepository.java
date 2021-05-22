package com.derevetskyi.markiyan.adminmicroservice.repository;

import com.derevetskyi.markiyan.adminmicroservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
