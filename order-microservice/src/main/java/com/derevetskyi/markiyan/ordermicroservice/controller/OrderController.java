package com.derevetskyi.markiyan.ordermicroservice.controller;

import com.derevetskyi.markiyan.ordermicroservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void makeOrder(@RequestParam Long productId, @RequestParam Long userId) {
        orderService.makeOrder(productId, userId);
    }

    @GetMapping("/{id}/pay")
    public void payOrder(@PathVariable Long id) {
        orderService.payOrder(id);
    }
}
