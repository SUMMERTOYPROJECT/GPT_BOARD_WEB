package com.jyp_board.board_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.empty();
            }

            // 여기서는 인증된 사용자의 username을 사용자 ID로 가정합니다.
            // 실제 사용자 ID를 사용하려면, authentication.getPrincipal()에서
            // 적절한 타입으로 캐스팅하여 필요한 정보를 추출해야 합니다.

            return Optional.ofNullable(authentication.getName());
        }
    }
}
