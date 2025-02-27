package com.goorm.bakkuyoungapi.domain.product.dao;

import com.goorm.bakkuyoungapi.domain.product.domain.QWishProduct;
import com.goorm.bakkuyoungapi.domain.product.domain.WishProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishProductQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public WishProductQuerydslRepository(JPAQueryFactory queryFactory) {
        super(WishProduct.class);
        this.queryFactory = queryFactory;
    }

    QWishProduct wishProduct = QWishProduct.wishProduct;

    public List<String> findWishProductNamesByProductNo(Long productNo) {
        return queryFactory
                .select(wishProduct.wishProductName)
                .from(wishProduct)
                .where(wishProduct.productNo.eq(productNo))
                .fetch();
    }

}
