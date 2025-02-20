package com.goorm.bakkuyoungapi.domain.swap.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwapCreate {

    private List<Long> productNos;

}
