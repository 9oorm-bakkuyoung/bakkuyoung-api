package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.member.service.MemberService;
import com.goorm.bakkuyoungapi.domain.product.dao.ProductRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final MemberService memberService;

    private final ProductRepository productRepository;
    private final String uploadDir = "uploads/";

    public void create(CreateProduct createProduct, MultipartFile file) throws IOException {

        String imageUrl = saveImage(file);

        Product product = new Product();
        product.setPrice(createProduct.getPrice());
        product.setDescription(createProduct.getDescription());
        product.setImageUrl(imageUrl);
        product.setMemberId(memberService.getMemberId());

        productRepository.save(product);

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
}
