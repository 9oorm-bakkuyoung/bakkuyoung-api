package com.goorm.bakkuyoungapi.domain.product.dao;


import com.goorm.bakkuyoungapi.domain.product.domain.Product;
import com.goorm.bakkuyoungapi.domain.product.domain.QProduct;
import com.goorm.bakkuyoungapi.domain.product.dto.response.ProductDetail;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ProductQuerydslRepository(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }


    QProduct product = QProduct.product;


    public List<ProductDetail> findAllMembers() {
        return queryFactory
                .select(Projections.constructor(ProductDetail.class,
                        product.productNo,
                        product.price,
                        product.description,
                        product.imageUrl,
                        product.memberNo,
                        product.heartYn))
                .from(product)
                .fetch();
    }


}
