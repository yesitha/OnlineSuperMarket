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

    @KafkaListener(topics = "deliveryPersonAssigned", groupId = "notification")
    public void handleNotificationDP(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
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

    @KafkaListener(topics = "orderPlaced", groupId = "notification")
    public void OrderPlacedNotify(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        int customerId = jsonNode.get("customerId").asInt();
        int orderNumber = jsonNode.get("orderNumber").asInt();
        System.out.println("Sending notification to Customer & Inventory Keeper. Customer: " + customerId +"placed order :" + orderNumber);


    }

    @KafkaListener(topics = "orderCanceled", groupId = "notification")
    public void OrderCanceledNotify(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        int customerId = jsonNode.get("customerId").asInt();
        int orderNumber = jsonNode.get("orderNumber").asInt();
        System.out.println("Sending notification to Customer & Inventory Keeper. Customer: " + customerId +"canceled order :" + orderNumber);


    }

    @KafkaListener(topics = "orderProcessed", groupId = "notification")
    public void OrderProcessedNotify(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        int customerId = jsonNode.get("customerId").asInt();
        int orderNumber = jsonNode.get("orderNumber").asInt();
        System.out.println("Sending notification to Customer . Customer: " + customerId +",order processed :" + orderNumber);


    }

    @KafkaListener(topics = "orderPickedUp", groupId = "notification")
    public void OrderPickedUpNotify(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        int customerId = jsonNode.get("customerId").asInt();
        int orderNumber = jsonNode.get("orderNumber").asInt();
        System.out.println("Sending notification to Customer . Customer: " + customerId +", Your order Picked up by delivery guy :" + orderNumber);


    }



    @KafkaListener(topics = " orderDispached", groupId = "notification")
    public void OrderDispachedNotify(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        int customerId = jsonNode.get("customerId").asInt();
        int orderNumber = jsonNode.get("orderNumber").asInt();
        System.out.println("Sending notification to Customer . Customer: " + customerId +", Your order Dispached  :" + orderNumber);


    }
    @KafkaListener(topics = "orderDelivered", groupId = "notification")
    public void OrderDeliveredNotify(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        int customerId = jsonNode.get("customerId").asInt();
        int orderNumber = jsonNode.get("orderNumber").asInt();
        System.out.println("Sending notification to Customer . Customer: " + customerId +", Your order Dispached  :" + orderNumber);


    }

}


