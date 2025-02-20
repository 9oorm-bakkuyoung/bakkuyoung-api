package com.goorm.bakkuyoungapi.domain.swap.api;

import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import com.goorm.bakkuyoungapi.domain.swap.dto.request.SwapCreate;
import com.goorm.bakkuyoungapi.domain.swap.service.SwapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Swap", description = "물물교환")
@RestController
@RequestMapping("/api/swap")
@RequiredArgsConstructor
public class SwapController {

    private final SwapService swapService;

    @Operation(summary = "교환 등록", description = "교환 신청할 시")
    @PostMapping(value = "/add")
    public ResponseDto<Long> create(@RequestBody SwapCreate swapCreate) {
        return ResponseDto.of(swapService.create(swapCreate));
    }

    @Operation(summary = "교환수락", description = "교환")
    @PostMapping(value = "/add")
    public ResponseDto<String> accept(@RequestBody Long swapNo) {
        swapService.accept(swapNo);
        return ResponseDto.ok();
    }
}
