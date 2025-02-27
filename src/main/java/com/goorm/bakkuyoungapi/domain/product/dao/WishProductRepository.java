package com.goorm.bakkuyoungapi.domain.product.dao;

import com.goorm.bakkuyoungapi.domain.product.domain.WishProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishProductRepository extends JpaRepository<WishProduct, Long> {
}
