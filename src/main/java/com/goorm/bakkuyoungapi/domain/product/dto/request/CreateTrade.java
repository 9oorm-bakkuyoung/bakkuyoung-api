package com.goorm.bakkuyoungapi.domain.product.dto.request;

import com.goorm.bakkuyoungapi.domain.product.domain.TradeRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrade {
    private Long requestedProductNo;
    private Long offeredProductNo;

    public TradeRequest toTradeRequest() {
        return TradeRequest.builder()
                .requestedProductNo(requestedProductNo)
                .offeredProductNo(offeredProductNo)
                .build();
    }
}
