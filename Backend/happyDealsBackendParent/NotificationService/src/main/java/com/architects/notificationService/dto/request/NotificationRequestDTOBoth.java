package com.architects.notificationService.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NotificationRequestDTOBoth {
    private String userPreference;
    private String message;
    private String receiverEmail;
    private String receiverPhoneNumber;
    private String subject;
}
