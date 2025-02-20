package com.goorm.bakkuyoungapi.domain.product.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {


    private Long productNo;

    private double price;

    private String description;

    private String imageUrl;

    private Long memberNo;

    private String heartYn;
}
