package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.product.dao.WishProductRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.WishProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishProductRepository wishProductRepository;

    public void create(List<String> names, Long productNo) {
        for (String name : names) {
            WishProduct wishProduct = new WishProduct();
//            wishProduct.setProductNo(productNo);
//            wishProduct.setWishName(name);
            wishProductRepository.save(wishProduct);
        }
    }



}
