package com.derevetskyi.markiyan.ordermicroservice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "user_id")
    private Long userId;
}
