package com.example.lw3.service;

import com.example.lw3.dto.OrderDto;
import com.example.lw3.dto.OrderPostDto;

public interface OrderService {
    OrderDto makeOrder(OrderPostDto orderPostDto);

    OrderDto payOrder(Long id);
}
