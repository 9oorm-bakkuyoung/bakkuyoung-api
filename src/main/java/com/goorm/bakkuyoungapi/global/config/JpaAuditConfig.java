package com.goorm.bakkuyoungapi.global.config;

import com.goorm.bakkuyoungapi.global.config.auth.AuditAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class JpaAuditConfig {
    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditAwareImpl();
    }
}
