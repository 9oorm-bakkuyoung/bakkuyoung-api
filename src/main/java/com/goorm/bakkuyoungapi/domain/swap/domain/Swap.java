package com.goorm.bakkuyoungapi.domain.swap.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class Swap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swapId;

    private SwapStatus status;



}
