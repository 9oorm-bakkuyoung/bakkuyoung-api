package com.goorm.bakkuyoungapi.domain.product.api;

import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.response.ProductDetail;
import com.goorm.bakkuyoungapi.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PRODUCT", description = "상품")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록", description = "내 상품을 등록함")
    @PostMapping(value = "/add")
    public ResponseDto<String> save(@RequestBody CreateProduct createProduct) {
        productService.create(createProduct);
        return ResponseDto.ok();
    }

    @Operation(summary = "전체상품 조회", description = "교환 가능 상품을 조회함")
    @GetMapping("/all")
    public ResponseDto<List<ProductDetail>> getProducts() {
        List<ProductDetail> productDetails = productService.getAllProducts();
        return ResponseDto.of(productDetails);
    }

}
