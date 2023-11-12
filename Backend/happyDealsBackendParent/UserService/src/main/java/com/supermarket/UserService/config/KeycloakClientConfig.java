package com.supermarket.UserService.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8181/auth")
                .realm("Supermarket")
                .clientId("supermarket-backend")
                .username("admin")
                .password("admin")
                .build();
    }
}
