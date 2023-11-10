package com.architects.notificationService.services;

import org.springframework.web.reactive.function.client.WebClient;

public class SampleService {
    private final WebClient.Builder webClientBuilder;

    public SampleService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
}
