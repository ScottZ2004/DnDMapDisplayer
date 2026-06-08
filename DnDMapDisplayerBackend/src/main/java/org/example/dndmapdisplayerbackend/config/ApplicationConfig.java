package org.example.dndmapdisplayerbackend.config;

import org.example.dndmapdisplayerbackend.adapters.out.persistence.UserRepository;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.UserRepositoryAdapter;
import org.example.dndmapdisplayerbackend.application.service.UserService;
import org.example.dndmapdisplayerbackend.domain.port.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    UserRepositoryPort userRepositoryPort(
            UserRepository repository) {

        return new UserRepositoryAdapter(repository);
    }

    @Bean
    UserService userService(
            UserRepositoryPort userRepositoryPort) {

        return new UserService(userRepositoryPort);
    }
}