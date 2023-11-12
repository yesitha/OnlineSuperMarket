package com.architects.notificationService.controllers;


import com.architects.notificationService.dto.request.NotificationRequestDTO;
import com.architects.notificationService.dto.request.NotificationRequestDTOBoth;
import com.architects.notificationService.services.NotificationService;
import com.architects.notificationService.services.notificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/sendSMSorEmail")
    public void sendNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
        notificationServiceInterface notification = notificationService.createNotification(notificationRequestDTO.getUserPreference());

        notification.sendNotification(notificationRequestDTO.getMessage(), notificationRequestDTO.getReceiver(), notificationRequestDTO.getSubject());

    }

//    @PostMapping("/sendBoth")
//    public void sendBothNotifications(@RequestBody NotificationRequestDTOBoth notificationRequestDTO) {
//        notificationServiceInterface Emailnotification = notificationService.createNotification("email");
//        Emailnotification.sendNotification(notificationRequestDTO.getMessage(), notificationRequestDTO.getReceiverEmail(), notificationRequestDTO.getSubject());
//        notificationServiceInterface SMSnotification = notificationService.createNotification("sms");
//        SMSnotification.sendNotification(notificationRequestDTO.getMessage(), notificationRequestDTO.getReceiverPhoneNumber(), notificationRequestDTO.getSubject());
//        }

}

