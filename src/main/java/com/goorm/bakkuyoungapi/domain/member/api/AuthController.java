package com.goorm.bakkuyoungapi.domain.member.api;

import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import com.goorm.bakkuyoungapi.domain.member.dto.request.LoginRequest;
import com.goorm.bakkuyoungapi.domain.member.dto.request.MemberRequest;
import com.goorm.bakkuyoungapi.domain.member.dto.response.LoginResponse;
import com.goorm.bakkuyoungapi.domain.member.dto.response.MemberDetail;
import com.goorm.bakkuyoungapi.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AUTH", description = "Auth API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @Operation(summary = "회원 등록", description = "회원을 등록함.")
    @PostMapping("/signup")
    ResponseDto<MemberDetail> save(@RequestBody MemberRequest memberRequest) {
        MemberDetail member = memberService.save(memberRequest);
        return ResponseDto.of(member);
    }


    @Operation(summary = "로그인", description = "일반 멤버용 로그인")
    @PostMapping("/login")
    public ResponseDto<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        memberService.login(loginRequest, request);
        return ResponseDto.ok();
    }


    @Operation(summary = "멤버 정보", description = "로그인된 멤버의 정보를 조회함.")
    @GetMapping("/me")
    public ResponseDto<MemberDetail> getCurrentUser() {
        MemberDetail response = memberService.getCurrentMember();
        return ResponseDto.of(response);
    }

    // todo. 로그아웃
}
