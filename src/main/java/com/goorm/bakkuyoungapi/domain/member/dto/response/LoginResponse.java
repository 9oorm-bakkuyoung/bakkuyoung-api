package com.goorm.bakkuyoungapi.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String id;
    private String accessToken;
}
