package com.goorm.bakkuyoungapi.domain.member.dto.request;

import com.goorm.bakkuyoungapi.domain.member.domain.Member;
import com.goorm.bakkuyoungapi.domain.member.domain.MemberType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//멤버 등록,수정시 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotBlank(message = "이름을 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;

    @NotBlank(message = "이름를 입력하세요.")
    private String memberName;


    //일반사용자용
    public Member toMember(String password) {
        return Member.builder()
                .email(email)
                .password(password)
                .memberName(memberName)
                .build();
    }


}
