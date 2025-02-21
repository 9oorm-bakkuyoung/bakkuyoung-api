package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.product.dao.WishRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    public void create(List<String> names, Long productNo) {
        for (String name : names) {
            Wish wish = new Wish();
            wish.setProductNo(productNo);
            wish.setWishName(name);
            wishRepository.save(wish);
        }
    }

    public List<String> getWishList(Long productNo) {
        List<Wish> wishList = wishRepository.findByProductNo(productNo);
        return wishList.stream().map(Wish::getWishName).collect(Collectors.toList());
    }

}
