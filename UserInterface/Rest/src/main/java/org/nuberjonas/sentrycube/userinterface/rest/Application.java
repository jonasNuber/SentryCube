package org.nuberjonas.sentrycube.userinterface.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.nuberjonas.sentrycube.userinterface.rest", "org.nuberjonas.sentrycube.infrastructure.persistence.jpa"})
@EnableJpaRepositories(basePackages = {"org.nuberjonas.sentrycube.infrastructure.persistence.jpa"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
