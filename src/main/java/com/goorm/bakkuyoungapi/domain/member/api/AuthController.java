package com.goorm.bakkuyoungapi.domain.member.api;

import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import com.goorm.bakkuyoungapi.domain.member.dto.request.Join;
import com.goorm.bakkuyoungapi.domain.member.dto.request.LoginRequest;
import com.goorm.bakkuyoungapi.domain.member.dto.response.LoginResponse;
import com.goorm.bakkuyoungapi.domain.member.dto.response.MemberDetail;
import com.goorm.bakkuyoungapi.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AUTH", description = "Auth API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "회원을 등록함.")
    @PostMapping("/join")
    ResponseDto<MemberDetail> join(@RequestBody Join join) {
        MemberDetail member = memberService.join(join);
        return ResponseDto.of(member);
    }

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ResponseDto<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return ResponseDto.of(memberService.login(loginRequest));
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @PostMapping("/logout")
    public ResponseDto<String> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        memberService.logout(authentication, request, response);
        return ResponseDto.ok();
    }

    @Operation(summary = "멤버 정보", description = "로그인된 멤버의 정보를 조회함.")
    @GetMapping("/me")
    public ResponseDto<MemberDetail> getCurrentUser() {
        MemberDetail response = memberService.getCurrentMemberDetail();
        return ResponseDto.of(response);
    }

}
