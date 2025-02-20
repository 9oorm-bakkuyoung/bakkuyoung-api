package com.goorm.bakkuyoungapi.domain.member.domain;

import com.goorm.bakkuyoungapi.global.code.EnumCodeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType implements EnumCodeType {

    NORMAL("MBT01", "일반"),

    ;

    private final String codeValue;
    private final String codeDesc;
}
