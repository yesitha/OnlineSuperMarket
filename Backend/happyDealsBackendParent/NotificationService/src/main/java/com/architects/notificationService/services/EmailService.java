package com.architects.notificationService.services;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmailService implements notificationServiceInterface {
    private final WebClient.Builder webClientBuilder;

    public EmailService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public void sendNotification(String message, String recipient, String subject) {
        // ... (your email service logic)
        // Make sure to inject the necessary dependencies and configure the email service as needed.

    }


}

