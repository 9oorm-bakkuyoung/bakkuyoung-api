package com.goorm.bakkuyoungapi.domain.product.dao;

import com.goorm.bakkuyoungapi.domain.product.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByProductNo(Long productNo);
}
