package com.goorm.bakkuyoungapi.domain.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(nullable = false, unique = true, length = 30)
    private String email; //아이디로 사용

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType memberType = MemberType.NORMAL;


    @Builder
    private Member(String email, String password, String memberName) {
        this.email = email;
        this.password = password;
        this.memberName = memberName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(memberType.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}