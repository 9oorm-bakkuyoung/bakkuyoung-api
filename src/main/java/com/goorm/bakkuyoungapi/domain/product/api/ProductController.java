package com.goorm.bakkuyoungapi.domain.product.api;

import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateProduct;
import com.goorm.bakkuyoungapi.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "PRODUCT", description = "상품")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록", description = "내 상품을 등록함")
    @PostMapping("/signup")
    ResponseDto<String> save(@RequestBody CreateProduct createProduct,
                             @RequestParam("image") MultipartFile image) throws IOException {
        productService.create(createProduct, image);
        return ResponseDto.ok();
    }

}
