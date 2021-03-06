package com.derevetskyi.markiyan.productmicroservice.repository;

import com.derevetskyi.markiyan.productmicroservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    @Query("select p from Product p " +
        "where lower(p.name) like lower(concat('%', :filter, '%')) " +
        "or lower(p.type) like lower(concat('%', :filter, '%')) " +
        "or lower(p.country) like lower(concat('%', :filter, '%'))")
    List<Product> searchByFilter(String filter);

    @Query("select distinct(p.type) from Product p")
    List<String> findAllCategories();

    @Query("select p from Product p where p.price >= :from and p.price <= :to")
    List<Product> findByPrice(BigDecimal from, BigDecimal to);
}
