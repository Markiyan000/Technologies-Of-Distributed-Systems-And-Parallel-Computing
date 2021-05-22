package com.derevetskyi.markiyan.ordermicroservice.service.impl;

import com.derevetskyi.markiyan.ordermicroservice.entity.Order;
import com.derevetskyi.markiyan.ordermicroservice.repository.OrderRepository;
import com.derevetskyi.markiyan.ordermicroservice.service.OrderService;
import com.derevetskyi.markiyan.ordermicroservice.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void makeOrder(Long productId, Long userId) {
        ProductVO productVO = restTemplate
            .getForObject("http://PRODUCT-MICROSERVICE/products/" + productId, ProductVO.class);
        if( productVO == null) {
            throw new RuntimeException("Cannot find product with id ---> " + productId);
        }
        Order order = Order.builder()
            .creationDate(LocalDateTime.now())
            .price(productVO.getPrice())
            .userId(userId)
            .build();
        orderRepository.save(order);
    }

    @Override
    public void payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Cannot find order with id ---> " + orderId));
        order.setIsPaid(true);
    }
}
