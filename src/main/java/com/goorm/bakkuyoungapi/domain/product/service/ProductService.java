package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.member.service.MemberService;
import com.goorm.bakkuyoungapi.domain.product.dao.ProductQuerydslRepository;
import com.goorm.bakkuyoungapi.domain.product.dao.ProductRepository;
import com.goorm.bakkuyoungapi.domain.product.dao.WishProductRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import com.goorm.bakkuyoungapi.domain.product.domain.WishProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.response.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final MemberService memberService;

    private final ProductQuerydslRepository productQuerydslRepository;
    private final WishProductRepository wishProductRepository;
    private final ProductRepository productRepository;
    private final String uploadDir = "uploads/";

    @Transactional
    public void create(CreateProduct createProduct) {

        //todo. 이미지 저장 구현
//        String imageUrl = saveImage(file);

        //상품저장
        Product product = createProduct.toProduct(null);
        product = productRepository.save(product);

        //교환희망상품저장
        Long productNo = product.getProductNo();
        List<WishProduct> wishProducts = createProduct.getWishList().stream()
                .map(wish -> new WishProduct(wish, productNo))
                .collect(Collectors.toList());

        wishProductRepository.saveAll(wishProducts);
    }

    // 이미지 저장 로직
    private String saveImage(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new RuntimeException("이미지가 없습니다.");
        }

        // 디렉토리 생성
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 파일 저장
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        File file = new File(uploadDir + fileName);
        image.transferTo(file);

        return "/uploads/" + fileName; // 저장된 이미지의 URL
    }

    public List<ProductDetail> getAllProducts() {
        return productQuerydslRepository.findAllProducts();
    }
    public List<String> getWishList(Long productNo) {
        List<WishProduct> wishProductList = wishProductRepository.findByProductNo(productNo);
        return wishProductList.stream().map(WishProduct::getWishProductName).collect(Collectors.toList());
    }
}
