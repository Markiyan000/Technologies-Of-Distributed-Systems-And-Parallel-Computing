package com.example.lw3.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Component
public class CustomOrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public BigDecimal calculateDebtByUserId(Long userId) {
        return (BigDecimal) entityManager.createNativeQuery("select sum(o.price) from order_ o where o.user_id = :userId and o.is_paid = false")
            .setParameter("userId", userId)
            .getSingleResult();
    }
}
