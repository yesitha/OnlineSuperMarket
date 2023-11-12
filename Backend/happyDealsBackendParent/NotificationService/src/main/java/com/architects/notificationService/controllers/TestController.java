
// this code part is to check if the sms is sending or not. Not assential to the system.

package com.architects.notificationService.controllers;


import com.architects.notificationService.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    private final NotificationService notificationService;

    @Autowired
    public TestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/sendTestSms")
    public String sendTestSms() {
        notificationService.sendTestSms();
        return "Test SMS sent!";
    }

    @GetMapping("/sendTestEmail")
    public String sendTestEmail() {
        // Replace the parameters with your test email details
        String to = "prasadeesankalpana@gmail.com";
        String subject = "Test Email Subject";
        String body = "This is a test email message.";

        notificationService.sendEmail(to, subject, body);
        return "Test Email sent!";
    }
}
