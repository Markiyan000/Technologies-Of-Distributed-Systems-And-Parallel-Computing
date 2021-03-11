package com.example.lw3.service;

import com.example.lw3.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(OrderDto orderDto);
}
