package com.example.lw3.repository;

import com.example.lw3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    @Query("select p from Product p " +
        "where lower(p.name) like lower(concat('%', :filter, '%')) " +
        "or lower(p.type) like lower(concat('%', :filter, '%')) " +
        "or lower(p.country) like lower(concat('%', :filter, '%'))")
    List<Product> searchByFilter(String filter);
}
