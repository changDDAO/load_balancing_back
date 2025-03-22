package com.changddao.load_balancing_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LoadBalancingBackApplication {

    public static void main(String[] args) {SpringApplication.run(LoadBalancingBackApplication.class, args);
    }

}
