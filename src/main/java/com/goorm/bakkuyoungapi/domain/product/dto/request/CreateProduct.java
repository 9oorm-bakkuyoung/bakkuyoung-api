package com.goorm.bakkuyoungapi.domain.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {

    private double price;

    private String description;

    private String heartYn;

    private List<String> wishList;

}
