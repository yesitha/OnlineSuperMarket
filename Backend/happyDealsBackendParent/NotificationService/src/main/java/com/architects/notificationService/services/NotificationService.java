package com.architects.notificationService.services;



import com.infobip.model.SmsTextualMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SmsService smsService;
    private final EmailService emailService;

    @Autowired
    public NotificationService(SmsService smsService, EmailService emailService) {
        this.smsService = smsService;
        this.emailService = emailService;
    }

    public void sendSms(String recipient, String message) {
        smsService.sendSms(recipient, message);
    }

    public void sendTestSms() {
        // Sending a simple "hello" message for testing
        smsService.sendSms("94716667678", "hello");// replace the 94716667678 with valid phone number when running the code.
    }


    public void sendEmail(String to, String subject, String body) {
        //emailService.sendEmail(to, subject, body);
    }
}

