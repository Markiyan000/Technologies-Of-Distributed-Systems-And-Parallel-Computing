package com.example.lw3.service;

import com.example.lw3.dto.OrderDto;

public interface EmailService {
    void sendEmail(OrderDto orderDto);
}
