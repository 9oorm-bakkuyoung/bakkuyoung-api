package com.goorm.bakkuyoungapi.domain.member.service;

import com.goorm.bakkuyoungapi.domain.member.dao.MemberQuerydslRepository;
import com.goorm.bakkuyoungapi.domain.member.dao.MemberRepository;
import com.goorm.bakkuyoungapi.domain.member.domain.Member;
import com.goorm.bakkuyoungapi.domain.member.dto.request.Join;
import com.goorm.bakkuyoungapi.domain.member.dto.request.LoginRequest;
import com.goorm.bakkuyoungapi.domain.member.dto.response.LoginResponse;
import com.goorm.bakkuyoungapi.domain.member.dto.response.MemberDetail;
import com.goorm.bakkuyoungapi.global.security.LoginUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    private final MemberRepository memberRepository;

    private final LoginUtil loginUtil;

    //사용자 등록
    @Transactional
    public MemberDetail join(Join join) {
        Member member = join.toMember(bCryptPasswordEncoder.encode(join.getPassword()));
        member = memberRepository.save(member);

        return new MemberDetail(member.getMemberNo(), member.getId(), member.getMemberName());
    }

    //로그인
    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getId(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // 세션을 통해 인증 정보 저장 (JWT 미사용 방식)
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        // 로그인 성공 응답 생성
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new LoginResponse(userDetails.getUsername(), "로그인 성공");
    }

    //로그아웃
    public void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
    }


    //로그인된 사용자정보 확인
    public MemberDetail getCurrentMember() {
        Optional<Member> member = memberRepository.findById(loginUtil.getMemberNo());
        if (member.isPresent()) {
            Member currentMember = member.get();
            return new MemberDetail(currentMember.getMemberNo(), currentMember.getId(), currentMember.getMemberName());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }
    }

}
