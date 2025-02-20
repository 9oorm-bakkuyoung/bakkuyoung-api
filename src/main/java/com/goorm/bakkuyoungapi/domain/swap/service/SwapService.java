package com.goorm.bakkuyoungapi.domain.swap.service;

import com.goorm.bakkuyoungapi.domain.product.dao.ProductRepository;
import com.goorm.bakkuyoungapi.domain.swap.dao.SwapItemRepository;
import com.goorm.bakkuyoungapi.domain.swap.dao.SwapRepository;
import com.goorm.bakkuyoungapi.domain.swap.domain.Swap;
import com.goorm.bakkuyoungapi.domain.swap.domain.SwapItem;
import com.goorm.bakkuyoungapi.domain.swap.domain.SwapStatus;
import com.goorm.bakkuyoungapi.domain.swap.dto.request.SwapCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwapService {

    private final SwapRepository swapRepository;
    private final SwapItemRepository swapItemRepository;

    private final ProductRepository productRepository;


    public Long create(SwapCreate swapCreate) {
        Swap swap = new Swap();
        swap.setStatus(SwapStatus.PENDING);
        swap = swapRepository.save(swap);

        Long swapNo = swap.getSwapNo();

        for (Long productNo : swapCreate.getProductNos()) {
            SwapItem swapItem = new SwapItem();
            swapItem.setSwapItemNo(productNo);
            swapItem.setSwapNo(swapNo);
            swapItemRepository.save(swapItem);

            Long memberNo = productRepository.findMemberNoByProductNo(productNo).get().getMemberNo();

        }

        return swapNo;
    }

    public void accept(Long swapNo) {
        swapRepository.findById(swapNo).ifPresent(swap -> {
            swap.setStatus(SwapStatus.MATCHED);
        });
    }


}
