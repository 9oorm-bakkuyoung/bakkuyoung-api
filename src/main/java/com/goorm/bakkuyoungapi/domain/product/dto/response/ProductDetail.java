package com.goorm.bakkuyoungapi.domain.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {


    private Long productNo;

    private String productName;

    private String description;

    private String imageUrl;

    private Long creatorNo;

    private String heartYn;
}
