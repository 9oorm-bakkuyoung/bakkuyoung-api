package com.goorm.bakkuyoungapi.domain.member.service;

import com.goorm.bakkuyoungapi.domain.member.dao.MemberQuerydslRepository;
import com.goorm.bakkuyoungapi.domain.member.dao.MemberRepository;
import com.goorm.bakkuyoungapi.domain.member.domain.Member;
import com.goorm.bakkuyoungapi.domain.member.dto.request.LoginRequest;
import com.goorm.bakkuyoungapi.domain.member.dto.request.MemberRequest;
import com.goorm.bakkuyoungapi.domain.member.dto.response.LoginResponse;
import com.goorm.bakkuyoungapi.domain.member.dto.response.MemberDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final MemberQuerydslRepository memberQuerydslRepository;

    //사용자 등록
    @Transactional
    public MemberDetail save(MemberRequest memberRequest) {
        Member member = memberRequest.toMember(bCryptPasswordEncoder.encode(memberRequest.getPassword()));
        member = memberRepository.save(member);

        return new MemberDetail(member.getMemberNo(), member.getEmail(), member.getMemberName());
    }

    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

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


    //사용자 정보 확인
    public MemberDetail getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        UserDetails userDetails = getUserDetails();

        Optional<Member> member = memberRepository.findByEmail(userDetails.getUsername());
        if (member.isPresent()) {
            Member currentMember = member.get();
            MemberDetail detail = new MemberDetail(currentMember.getMemberNo(), currentMember.getEmail(), currentMember.getMemberName());
            return detail;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

    }

    public Long getMemberNo() {
        UserDetails userDetails = getUserDetails();

        Optional<Member> member = memberRepository.findByEmail(userDetails.getUsername());
        if (member.isPresent()) {
            Member currentMember = member.get();
            return currentMember.getMemberNo();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }
    }

    public UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        return (UserDetails) authentication.getPrincipal();
    }

    public List<MemberDetail> getAllMembers() {
        return memberQuerydslRepository.findAllMembers();
    }


}
