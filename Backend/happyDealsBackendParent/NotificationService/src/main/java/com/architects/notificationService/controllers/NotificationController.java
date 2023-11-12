package com.architects.notificationService.controllers;


import com.architects.notificationService.services.NotificationService;
import com.architects.notificationService.services.notificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/send/{userPreference}/{message}/{receiver}/{Subject}")
    public void sendNotification(@PathVariable String userPreference, @PathVariable String message, @PathVariable String receiver, @PathVariable String subject) {
        notificationServiceInterface notification = notificationService.createNotification(userPreference);
        notification.sendNotification(receiver, subject, message);
    }

    @GetMapping("/sendBoth/{message}/{receiver}/{Subject}")
    public void sendBothNotifications(@PathVariable String message, @PathVariable String receiver, @PathVariable String subject) {
        List<notificationServiceInterface> notifications = notificationService.createBothNotifications();
        for (notificationServiceInterface notification : notifications) {
            notification.sendNotification(receiver, subject, message);
        }
    }
}

