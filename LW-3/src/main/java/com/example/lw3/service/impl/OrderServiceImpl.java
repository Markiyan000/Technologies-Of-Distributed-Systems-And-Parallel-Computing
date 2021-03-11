package com.example.lw3.service.impl;

import com.example.lw3.dto.OrderDto;
import com.example.lw3.dto.OrderPostDto;
import com.example.lw3.entity.Order;
import com.example.lw3.entity.Product;
import com.example.lw3.exception.OrderNotFoundException;
import com.example.lw3.exception.QuantityExceedsException;
import com.example.lw3.mapper.OrderMapper;
import com.example.lw3.repository.OrderRepository;
import com.example.lw3.repository.ProductRepository;
import com.example.lw3.service.EmailService;
import com.example.lw3.service.OrderService;
import com.example.lw3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.lw3.messages.Messages.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final EmailService emailService;

    @Override
    @Transactional
    public OrderDto makeOrder(OrderPostDto orderPostDto) {
        List<Long> ids = new ArrayList<>(orderPostDto.getIds().keySet());
        List<Integer> quantities = new ArrayList<>(orderPostDto.getIds().values());
        List<Product> foundProducts = productRepository.findByIds(ids);
        BigDecimal priceOfOrder = getPriceOfOrderAndDecreaseQuantity(foundProducts, quantities);

        Order newOrder = buildOrder(foundProducts, priceOfOrder);
        Order savedOrder = orderRepository.save(newOrder);
        OrderDto savedOrderDto = OrderMapper.toOrderDto(savedOrder);

        emailService.sendEmail(savedOrderDto);

        return savedOrderDto;
    }

    @Override
    @Transactional
    public OrderDto payOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND + id));
        order.setIsPaid(true);

        return OrderMapper.toOrderDto(order);
    }

    private Order buildOrder(List<Product> products, BigDecimal priceOfOrder) {
        return Order.builder()
            .creationDate(LocalDateTime.now())
            .price(priceOfOrder)
            .isPaid(false)
            .user(userService.getCurrentUser())
            .products(new HashSet<>(products))
            .build();
    }

    private BigDecimal getPriceOfOrderAndDecreaseQuantity(List<Product> products, List<Integer> quantities) {
        BigDecimal priceOfOrder = BigDecimal.ZERO;

        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            int currentQuantity = quantities.get(i);
            if (currentProduct.getQuantity() < currentQuantity) {
                throw new QuantityExceedsException(String.format
                    (QUANTITY_EXCEEDS, currentProduct.getId(), currentProduct.getQuantity(), currentQuantity));
            }

            priceOfOrder = priceOfOrder.add(currentProduct.getPrice().multiply(BigDecimal.valueOf(currentQuantity)));
            currentProduct.setQuantity(currentProduct.getQuantity() - currentQuantity);

        }

        return priceOfOrder;
    }
}
