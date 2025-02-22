package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.product.dao.ProductRepository;
import com.goorm.bakkuyoungapi.domain.product.dao.TradeRequestRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.TradeRequest;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateTrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TradeService {

    private final TradeRequestRepository tradeRequestRepository;
    private final ProductRepository productRepository;

    //교환신청
    public Long request(CreateTrade createTrade) {
        return tradeRequestRepository.save(createTrade.toTradeRequest()).getTradeRequestNo();
    }

    //교환승인(채팅방 번호 리턴)
    public Long accept(Long tradeRequestNo) {
        //교환 상태 변경
        tradeRequestRepository.findById(tradeRequestNo).ifPresent(TradeRequest::accept);
        //todo. 채팅방 생성후 채팅방번호 리턴
        return null;
    }

}
