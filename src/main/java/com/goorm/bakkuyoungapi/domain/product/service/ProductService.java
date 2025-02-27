package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.product.dao.ProductQuerydslRepository;
import com.goorm.bakkuyoungapi.domain.product.dao.ProductRepository;
import com.goorm.bakkuyoungapi.domain.product.dao.WishProductRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import com.goorm.bakkuyoungapi.domain.product.domain.WishProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.response.ProductDetail;
import com.goorm.bakkuyoungapi.global.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final FileService fileService;

    private final ProductQuerydslRepository productQuerydslRepository;
    private final WishProductRepository wishProductRepository;
    private final ProductRepository productRepository;
    private final String uploadDir = "uploads/";

    @Transactional
    public void create(CreateProduct createProduct, MultipartFile image) {

        String imageUrl = fileService.upload(image);

        //상품저장
        Product product = createProduct.toProduct(imageUrl);
        product = productRepository.save(product);

        //교환희망상품저장
        Long productNo = product.getProductNo();
        List<WishProduct> wishProducts = createProduct.getWishList().stream()
                .map(wish -> new WishProduct(wish, productNo))
                .collect(Collectors.toList());

        wishProductRepository.saveAll(wishProducts);
    }

    public List<ProductDetail> getAllProducts() {
        return productQuerydslRepository.findAllProducts();
    }
    public List<String> getWishList(Long productNo) {
        List<WishProduct> wishProductList = wishProductRepository.findByProductNo(productNo);
        return wishProductList.stream().map(WishProduct::getWishProductName).collect(Collectors.toList());
    }
}
