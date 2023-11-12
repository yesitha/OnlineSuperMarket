package com.architects.orderService.controllers;

import com.architects.orderService.services.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")

public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/assign-delivery-person-to-order/{orderId}/{deliveryPersonId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
//    @CircuitBreaker(name = "delivery", fallbackMethod = "assignDeliveryPersonToOrderFallback")
    public String assignDeliveryPersonToOrder(@PathVariable Long orderId, @PathVariable Long deliveryPersonId) {

        orderService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
        return "Delivery Person Assigned to Order Successfully";

    }

//    public String assignDeliveryPersonToOrderFallback(Long orderId, Long deliveryPersonId,RuntimeException e) {
//        return "Delivery Person Assigned to Order Failed !!! Please try again later";
//    }
}
