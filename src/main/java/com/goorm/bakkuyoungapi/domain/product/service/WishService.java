package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.member.service.MemberService;
import com.goorm.bakkuyoungapi.domain.product.dao.ProductRepository;
import com.goorm.bakkuyoungapi.domain.product.dao.WishRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import com.goorm.bakkuyoungapi.domain.product.domain.Wish;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishService {

   private final WishRepository wishRepository;

    public void create(List<String> names, Long productNo) {
        for(String name : names) {
            Wish wish = new Wish();
            wish.setProductNo(productNo);
            wish.setWishName(name);
            wishRepository.save(wish);
        }
    }


//    public List<String> getWishList(Long productNo) {
//
//    }


}
