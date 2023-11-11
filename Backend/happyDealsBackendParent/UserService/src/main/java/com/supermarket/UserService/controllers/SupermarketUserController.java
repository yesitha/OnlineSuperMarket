package com.supermarket.UserService.controllers;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/supermarket")
public class SupermarketUserController {

    private final Keycloak keycloak;

    public SupermarketUserController(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @GetMapping("/users")
    public List<UserRepresentation> getAllUsers() {
        return keycloak.realm("Supermarket").users().list();
    }
}
