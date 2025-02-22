package com.goorm.bakkuyoungapi.domain.product.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TradeType {
    PENDING, ACCEPTED, REJECTED;
}
