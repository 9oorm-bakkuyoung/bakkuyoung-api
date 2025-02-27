package com.goorm.bakkuyoungapi.domain.product.dao;


import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import com.goorm.bakkuyoungapi.domain.product.domain.QProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.response.ProductDetail;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ProductQuerydslRepository(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    QProduct product = QProduct.product;

    public Long getProductCreatorNo(Long tradeProductNo) {
        return queryFactory.select(product.creatorNo)
                .from(product)
                .where(product.productNo.eq(tradeProductNo))
                .fetchOne();
    }

    public List<ProductDetail> findAllProducts(Long memberNo) {
        return queryFactory
                .select(Projections.constructor(ProductDetail.class,
                        product.productNo,
                        product.productName,
                        product.description,
                        product.imageUrl,
                        product.heartYn,
                        product.latitude,
                        product.longitude,
                        Expressions.constant(Collections.emptyList()),
                        product.creatorNo))
                .from(product)
                .where(product.creatorNo.ne(memberNo))
                .fetch();
    }

    public Optional<ProductDetail> findProductByNo(Long productNo) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ProductDetail.class,
                        product.productNo,
                        product.productName,
                        product.description,
                        product.imageUrl,
                        product.heartYn,
                        product.latitude,
                        product.longitude,
                        Expressions.constant(Collections.emptyList()),
                        product.creatorNo))
                .from(product)
                .where(product.productNo.eq(productNo))
                .fetchOne());
    }

}
