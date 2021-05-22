package com.derevetskyi.markiyan.ordermicroservice.service;

public interface OrderService {
    void makeOrder(Long productId, Long userId);

    void payOrder(Long orderId);
}
