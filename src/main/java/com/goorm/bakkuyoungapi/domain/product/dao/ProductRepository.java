package com.goorm.bakkuyoungapi.domain.product.dao;

import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
