package com.architects.notificationService.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class NotificationService {
    private final WebClient.Builder webClientBuilder;

    private final SmsService smsService;
    private final EmailService emailService;

    @Autowired
    public NotificationService(WebClient.Builder webClientBuilder, SmsService smsService, EmailService emailService) {
        this.webClientBuilder = webClientBuilder;
        this.smsService = smsService;
        this.emailService = emailService;
    }

    public notificationServiceInterface createNotification(String userPreference
    ) {
        if ("email".equalsIgnoreCase(userPreference)) {
            return emailService;
        } else if ("sms".equalsIgnoreCase(userPreference)) {
            return smsService;
        } else {
            throw new IllegalArgumentException("Invalid notification type");
        }
    }

    public List<notificationServiceInterface> createBothNotifications() {
        return List.of(emailService, smsService);
    }



    public void sendSms(String recipient, String message) {
        smsService.sendSms(recipient, message);
    }

    public void sendTestSms() {
        // Sending a simple "hello" message for testing
        // replace the 94716667678 with valid phone number when running the code.
        smsService.sendSms("94716667678", "hello");

    }


    public void sendEmail(String to, String subject, String body) {
        //emailService.sendEmail(to, subject, body);
    }
}

