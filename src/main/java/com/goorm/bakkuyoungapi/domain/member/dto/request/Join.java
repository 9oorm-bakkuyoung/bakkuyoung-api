package com.goorm.bakkuyoungapi.domain.member.dto.request;

import com.goorm.bakkuyoungapi.domain.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Join {

    @NotBlank(message = "아이디를 입력하세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;

    @NotBlank(message = "이름를 입력하세요.")
    private String memberName;


    //일반사용자용
    public Member toMember(String password) {
        return Member.builder()
                .id(id)
                .password(password)
                .memberName(memberName)
                .build();
    }

}
