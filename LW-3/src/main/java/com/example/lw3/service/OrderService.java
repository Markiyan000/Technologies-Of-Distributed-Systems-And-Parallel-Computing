package com.example.lw3.service;

import com.example.lw3.dto.OrderDto;
import com.example.lw3.dto.OrderPostDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderDto makeOrder(OrderPostDto orderPostDto);
}
