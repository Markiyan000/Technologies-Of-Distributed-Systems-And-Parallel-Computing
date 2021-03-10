package com.example.lw3.mapper;

import com.example.lw3.dto.OrderDto;
import com.example.lw3.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
            .id(order.getId())
            .creationDate(order.getCreationDate())
            .price(order.getPrice())
            .userDto(UserMapper.toUserDto(order.getUser()))
            .productDtoS(order.getProducts().stream().map(ProductMapper::toProductDto).collect(Collectors.toSet()))
            .build();
    }
}
