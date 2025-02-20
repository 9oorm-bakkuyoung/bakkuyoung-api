package com.goorm.bakkuyoungapi.domain.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNo;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    private String imageUrl;

    private Long memberNo;

    @Column(nullable = false)
    private String heartYn = "N";

}