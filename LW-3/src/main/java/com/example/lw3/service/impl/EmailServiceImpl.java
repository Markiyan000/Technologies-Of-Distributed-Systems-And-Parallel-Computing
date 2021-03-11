package com.example.lw3.service.impl;

import com.example.lw3.dto.OrderDto;
import com.example.lw3.dto.ProductDto;
import com.example.lw3.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(OrderDto orderDto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(orderDto.getUserDto().getEmail());
        message.setSubject("Order from Online-Shop");
        message.setText("Hello!\nYour order is successfully processed!\nThank you!" +
            "\nDate:" + orderDto.getCreationDate() +
            "\nPrice of order: " + orderDto.getPrice() +
            "\nList of products:\n" + formatProducts(orderDto.getProductDtoS()));

        javaMailSender.send(message);
    }

    private String formatProducts(Set<ProductDto> products) {
        StringBuilder formattedText = new StringBuilder();
        products.forEach(p -> {
            formattedText.append("Name: ").append(p.getName()).append("\n")
                .append("Price: ").append(p.getPrice()).append("\n")
                .append("Type: ").append(p.getType()).append("\n")
                .append("Country: ").append(p.getCountry()).append("\n")
                .append("-------------------\n");
        });

        return formattedText.toString();
    }
}
