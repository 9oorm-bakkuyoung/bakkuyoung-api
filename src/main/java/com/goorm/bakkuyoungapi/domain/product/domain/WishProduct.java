package com.goorm.bakkuyoungapi.domain.product.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseOnlyTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wish_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WishProduct extends BaseOnlyTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishProductNo;

    @Column(nullable = false)
    private String wishProductName;

    private Long productNo;

}
