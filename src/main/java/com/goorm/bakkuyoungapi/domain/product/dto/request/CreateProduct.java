package com.goorm.bakkuyoungapi.domain.product.dto.request;

import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {

    private String productName;

    private String description;

    private String heartYn;

    private List<String> wishList;

    private BigDecimal latitude; //위도

    private BigDecimal longitude; //경도

    public Product toProduct(String imageUrl) {
        return Product.builder()
                .productName(productName)
                .description(description)
                .heartYn(heartYn)
                .latitude(latitude)
                .longitude(longitude)
                .imageUrl(imageUrl)
                .build();

    }

}
