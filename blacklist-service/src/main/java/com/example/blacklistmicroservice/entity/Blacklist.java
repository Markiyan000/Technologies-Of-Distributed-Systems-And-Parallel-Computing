package com.example.blacklistmicroservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "blacklist")
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "debt")
    private Integer debt;

    @Column(name = "user_id")
    private Long userId;
}
