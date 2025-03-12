package com.goorm.bakkuyoungapi.domain.member.service;

import com.goorm.bakkuyoungapi.domain.member.dao.MemberRepository;
import com.goorm.bakkuyoungapi.domain.member.domain.Member;
import com.goorm.bakkuyoungapi.domain.member.dto.request.Join;
import com.goorm.bakkuyoungapi.domain.member.dto.request.LoginRequest;
import com.goorm.bakkuyoungapi.domain.member.dto.response.LoginResponse;
import com.goorm.bakkuyoungapi.domain.member.dto.response.MemberDetail;
import com.goorm.bakkuyoungapi.global.config.auth.MemberDetailsImpl;
import com.goorm.bakkuyoungapi.global.config.jwt.JwtProvider;
import com.goorm.bakkuyoungapi.global.security.LoginUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    private final MemberRepository memberRepository;

    private final LoginUtil loginUtil;
    private final JwtProvider jwtProvider;

    //사용자 등록
    @Transactional
    public MemberDetail join(Join join) {
        Member member = join.toMember(bCryptPasswordEncoder.encode(join.getPassword()));
        member = memberRepository.save(member);

        return new MemberDetail(member.getMemberNo(), member.getId(), member.getMemberName());
    }

    //로그인
    public LoginResponse login(LoginRequest loginRequest) {
        //1. 인증객체 생성
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getId(), loginRequest.getPassword());

        //2. 인증 수행
        Authentication authentication = authenticationManager.authenticate(authToken);

        //3. 인증된 사용자 정보 가져옴
        MemberDetailsImpl userDetails = (MemberDetailsImpl) authentication.getPrincipal();

        //4. jwt 생성
        String accessToken = jwtProvider.generateAccessToken(userDetails.getUsername());
        return new LoginResponse(userDetails.getUsername(), accessToken);
    }

    //로그아웃
    public void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
    }


    //로그인된 사용자정보 확인
    public MemberDetail getCurrentMemberDetail() {
        MemberDetailsImpl memberDetails = loginUtil.getMemberInfo();
        return new MemberDetail(memberDetails.getMemberNo(), memberDetails.getUsername(), memberDetails.getMemberName());
    }

}
