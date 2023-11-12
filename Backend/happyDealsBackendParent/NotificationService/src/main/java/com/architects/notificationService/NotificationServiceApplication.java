package com.architects.notificationService;


import com.architects.notificationService.services.EmailService;
import com.architects.notificationService.services.NotificationService;
import com.architects.notificationService.services.SmsService;
import com.architects.notificationService.services.notificationServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "deliveryPersonAssigned")
    public void handleNotification(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the JSON string into a JsonNode
        JsonNode jsonNode = objectMapper.readTree(message);
        int deliveryPersonId = jsonNode.get("deliveryPersonId").asInt();
        int orderId = jsonNode.get("orderId").asInt();
        System.out.println("Sending notification to delivery person " + deliveryPersonId + " for order " + orderId);
//        String deliveryPersonEmail = "yesithaathukorala@gmail,com";
//        String deliveryPersonPhoneNumber ="94788754809";
//
//        final EmailService emailService = new EmailService();
//        final SmsService smsService= new SmsService();
//        emailService.sendEmail("You Assigned to Order"+orderId,"Order Assigned","Order Assigned");
//        smsService.sendSms(deliveryPersonPhoneNumber,"You Assigned to Order"+orderId);

    }
}


