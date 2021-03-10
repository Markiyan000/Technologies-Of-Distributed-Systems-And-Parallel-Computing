package com.example.lw3.service.impl;

import com.example.lw3.dto.OrderDto;
import com.example.lw3.dto.OrderPostDto;
import com.example.lw3.entity.Order;
import com.example.lw3.entity.Product;
import com.example.lw3.mapper.OrderMapper;
import com.example.lw3.repository.OrderRepository;
import com.example.lw3.repository.ProductRepository;
import com.example.lw3.service.OrderService;
import com.example.lw3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    @Transactional
    public OrderDto makeOrder(OrderPostDto orderPostDto) {
        List<Product> foundProducts = productRepository.findByIds(orderPostDto.getIds());
        Order newOrder = buildOrder(foundProducts);
        Order savedOrder = orderRepository.save(newOrder);
        foundProducts.forEach(p -> p.setQuantity(p.getQuantity() - 1));

        return OrderMapper.toOrderDto(savedOrder);
    }

    private Order buildOrder(List<Product> products) {
        return Order.builder()
            .creationDate(LocalDateTime.now())
            .price(products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
            .user(userService.getCurrentUser())
            .products(new HashSet<>(products))
            .build();
    }
}
