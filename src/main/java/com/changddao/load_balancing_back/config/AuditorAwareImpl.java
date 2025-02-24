package com.changddao.load_balancing_back.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        /*실제로는 Spring Security의 context에 저장된 값을 반환하나, 현재는 테스트용도*/
        return Optional.of("admin");
    }
}
