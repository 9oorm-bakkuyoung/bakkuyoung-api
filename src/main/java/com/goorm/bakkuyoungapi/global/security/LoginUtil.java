package com.goorm.bakkuyoungapi.global.security;

import com.goorm.bakkuyoungapi.global.config.auth.MemberDetailsImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginUtil {

    public MemberDetailsImpl getMemberInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        MemberDetailsImpl memberDetails = null;
        if (context.getAuthentication() != null) {
            Object principal = context.getAuthentication().getPrincipal();
            if (principal instanceof MemberDetailsImpl) {
                memberDetails = (MemberDetailsImpl) principal;
            }
        }
        return memberDetails;
    }

    public long getMemberNo() {
        if (!Objects.isNull(getMemberInfo())) return getMemberInfo().getMemberNo();
        else return -1;
    }

    public String getMemberName() {
        if (!Objects.isNull(getMemberInfo())) return getMemberInfo().getMemberName();
        else return "";
    }
}
