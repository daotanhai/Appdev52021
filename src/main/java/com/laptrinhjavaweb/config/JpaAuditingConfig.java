package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// get userName để insert vào 2 fields Created by, modified by
// JPA Auditing cho 4 Annotations: @CreatedDate | @LastModifiedDate | @CreatedBy | @LastModifiedBy
@Configuration

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {

        @Override
        public String getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // chưa đăng nhập / chưa có data
            if (authentication == null) {
                return null;
            }
            return authentication.getName(); // Đăng nhập xong sẽ lấy đc các thông tin của người đăng nhập. ( lấy username)
        }
    }
}
