package com.goorm.bakkuyoungapi.domain.product.service;

import com.goorm.bakkuyoungapi.domain.product.dao.ProductRepository;
import com.goorm.bakkuyoungapi.domain.product.dao.TradeRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRequestRepository tradeRequestRepository;
    private final ProductRepository productRepository;


//    public Long create(SwapCreate swapCreate) {
//        Swap swap = new Swap();
//        swap.setStatus(SwapStatus.PENDING);
//        swap = swapRepository.save(swap);
//
//        Long swapNo = swap.getSwapNo();
//
//        for (Long productNo : swapCreate.getProductNos()) {
//            SwapItem swapItem = new SwapItem();
//            swapItem.setSwapItemNo(productNo);
//            swapItem.setSwapNo(swapNo);
//            swapItemRepository.save(swapItem);
//
//            Long memberNo = productRepository.findMemberNoByProductNo(productNo).get().getCreatorNo();
//
//        }
//
//        return swapNo;
//    }
//
//    public void accept(Long swapNo) {
//        swapRepository.findById(swapNo).ifPresent(swap -> {
//            swap.setStatus(SwapStatus.MATCHED);
//        });
//    }


}
