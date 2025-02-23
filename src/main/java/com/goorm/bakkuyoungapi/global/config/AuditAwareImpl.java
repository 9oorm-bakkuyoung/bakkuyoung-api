package com.goorm.bakkuyoungapi.global.config;

import com.goorm.bakkuyoungapi.global.config.auth.MemberDetailsImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getName().equals("anonymousUser")) {
            return Optional.of(0L);
        }
        MemberDetailsImpl member = (MemberDetailsImpl) authentication.getPrincipal();
        return Optional.of(member.getMemberNo());
    }
}
