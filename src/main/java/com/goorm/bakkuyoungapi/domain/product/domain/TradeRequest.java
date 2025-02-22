package com.goorm.bakkuyoungapi.domain.product.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trade_request")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TradeRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TradeRequestNo;

    private Long requestedProductNo; // 교환신청상품

    private Long offeredProductNo; // 교환제안상품

    private TradeType tradeType;

}
