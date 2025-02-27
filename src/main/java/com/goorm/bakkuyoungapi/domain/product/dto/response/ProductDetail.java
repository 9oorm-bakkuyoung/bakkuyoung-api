package com.goorm.bakkuyoungapi.domain.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    private Long productNo;

    private String productName;

    private String description;

    private String imageUrl;

    private String heartYn;

    private BigDecimal latitude; //위도

    private BigDecimal longitude; //경도

    private List<String> wishProductNames;

    private Long creatorNo;

}
