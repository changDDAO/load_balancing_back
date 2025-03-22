package com.changddao.load_balancing_back;

import com.changddao.load_balancing_back.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LoadBalancingBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadBalancingBackApplication.class, args);
        System.out.printf("DB_USERNAME : %s", EnvConfig.get("DB_USERNAME"));
    }

}
