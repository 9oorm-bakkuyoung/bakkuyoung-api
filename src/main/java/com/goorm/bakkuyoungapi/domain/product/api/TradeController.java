package com.goorm.bakkuyoungapi.domain.product.api;

import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import com.goorm.bakkuyoungapi.domain.product.dto.request.CreateTrade;
import com.goorm.bakkuyoungapi.domain.product.service.TradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Trade", description = "교환")
@RestController
@RequestMapping("/api/trade")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @Operation(summary = "교환신청", description = "교환을 신청함")
    @PostMapping(value = "/request")
    public ResponseDto<Long> request(@RequestBody CreateTrade createTrade) {
        return ResponseDto.of(tradeService.request(createTrade));
    }

    @Operation(summary = "교환 승인", description = "교환신청을 승인함")
    @PutMapping(value = "/{tradeRequestNo}/request")
    public ResponseDto<Long> accept(@PathVariable Long tradeRequestNo) {
        return ResponseDto.of(tradeService.accept(tradeRequestNo));
    }

}
