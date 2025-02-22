package com.goorm.bakkuyoungapi.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDetail {

    private Long memberNo;

    private String id;

    private String memberName;

}
