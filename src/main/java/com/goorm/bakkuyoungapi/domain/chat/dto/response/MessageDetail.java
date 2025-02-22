package com.goorm.bakkuyoungapi.domain.chat.dto.response;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class MessageDetail {
    private String message;
    private Long creatorNo;
    private LocalDateTime createDatetime;
}
