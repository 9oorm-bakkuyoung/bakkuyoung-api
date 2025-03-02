package com.goorm.bakkuyoungapi.domain.product.api;

import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.response.ProductDetail;
import com.goorm.bakkuyoungapi.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "PRODUCT", description = "상품")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록", description = "내 상품을 등록함")
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<String> save(@RequestPart("product") CreateProduct createProduct, @RequestPart("image") MultipartFile image) {
        productService.create(createProduct, image);
        return ResponseDto.ok();
    }

    @Operation(summary = "전체상품 조회", description = "교환 가능 상품을 조회함")
    @GetMapping("/all")
    public ResponseDto<List<ProductDetail>> getAllProducts() {
        List<ProductDetail> productDetails = productService.getAllProducts();
        return ResponseDto.of(productDetails);
    }

    @Operation(summary = "상품 조회", description = "상품번호로 상품 정보를 조회함")
    @GetMapping("/{productNo}")
    public ResponseDto<ProductDetail> getProduct(@PathVariable Long productNo) {
        ProductDetail detail = productService.getProductByNo(productNo);
        return ResponseDto.of(detail);
    }

    @Operation(summary = "내 상품 조회", description = "내 교환 가능 상품을 조회함")
    @GetMapping("/my")
    public ResponseDto<List<ProductDetail>> getMyProducts() {
        List<ProductDetail> productDetails = productService.getMyProducts();
        return ResponseDto.of(productDetails);
    }

}
