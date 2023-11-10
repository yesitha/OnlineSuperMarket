package com.architects.orderService.controllers;

import com.architects.orderService.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")

public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/assign-delivery-person-to-order/{orderId}/{deliveryPersonId}")
    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId) {
        orderService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
    }
}
