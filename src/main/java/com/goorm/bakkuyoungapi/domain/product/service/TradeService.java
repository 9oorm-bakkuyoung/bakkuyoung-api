package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.chat.service.ChatService;
import com.goorm.bakkuyoungapi.domain.product.dao.TradeRequestRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.TradeRequest;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateTrade;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TradeService {

    private final ChatService chatService;
    private final TradeRequestRepository tradeRequestRepository;

    //교환신청
    public Long request(CreateTrade createTrade) {
        return tradeRequestRepository.save(createTrade.toTradeRequest()).getTradeRequestNo();
    }

    //교환승인(채팅방 번호 리턴)
    public Long accept(Long tradeRequestNo) {
        TradeRequest tradeRequest = tradeRequestRepository.findById(tradeRequestNo)
                .orElseThrow(() -> new EntityNotFoundException("TradeRequest not found: " + tradeRequestNo));

        tradeRequest.accept();
        return chatService.createChat(tradeRequest);
    }

}
