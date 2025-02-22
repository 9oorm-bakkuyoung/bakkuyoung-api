package com.goorm.bakkuyoungapi.global.config.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goorm.bakkuyoungapi.domain.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Long memberNo;

    private final String username;

    private final String memberName;

    @JsonIgnore
    private final String password;

    private final Collection<? extends  GrantedAuthority> authorities;

    public MemberDetailsImpl(Long memberNo, String username, String memberName, String password, Collection<? extends GrantedAuthority> authorities) {
        this.memberNo = memberNo;
        this.username = username;
        this.memberName = memberName;
        this.password = password;
        this.authorities = authorities;
    }

    public static MemberDetailsImpl build(Member member) {
        return new MemberDetailsImpl(
                member.getMemberNo(),
                member.getId(),
                member.getMemberName(),
                member.getPassword(),
                member.getAuthorities()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }
}
