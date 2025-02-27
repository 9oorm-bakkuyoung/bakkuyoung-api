package com.goorm.bakkuyoungapi.domain.product.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNo;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private String heartYn = "N";

    private BigDecimal latitude; //위도 todo.위치서비스

    private BigDecimal longitude; //경도

    @Builder
    private Product(String productName, String description, String imageUrl, String heartYn, BigDecimal latitude, BigDecimal longitude) {
        this.productName = productName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.heartYn = heartYn;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}