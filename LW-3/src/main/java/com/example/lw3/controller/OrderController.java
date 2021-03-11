package com.example.lw3.controller;

import com.example.lw3.dto.OrderDto;
import com.example.lw3.dto.OrderPostDto;
import com.example.lw3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> makeOrder(@RequestBody OrderPostDto orderPostDto) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(orderService.makeOrder(orderPostDto));
    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<OrderDto> payOrder(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(orderService.payOrder(id));
    }
}
