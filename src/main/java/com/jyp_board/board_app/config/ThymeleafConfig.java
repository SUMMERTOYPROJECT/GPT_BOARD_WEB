package com.jyp_board.board_app.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
@EnableConfigurationProperties(ThymeleafConfig.Thymeleaf3Properties.class) // Thymeleaf3Properties 클래스를 활성화합니다.
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
        return defaultTemplateResolver;
    }

    @Getter
    @RequiredArgsConstructor
    @ConfigurationProperties(prefix = "spring.thymeleaf3")
    public static class Thymeleaf3Properties {
        /**
         * Use Thymeleaf 3 Decoupled Logic
         */
        private boolean decoupledLogic; // final 제거

        // Setter 추가 (Lombok @Setter 어노테이션을 사용하거나 수동으로 setter 메서드를 정의할 수 있습니다.)
        public void setDecoupledLogic(boolean decoupledLogic) {
            this.decoupledLogic = decoupledLogic;
        }
    }
}
