package com.architects.orderService.controllers;

import com.architects.orderService.dto.response.OrderResponse;
import com.architects.orderService.dto.response.OrdersResponse;
import com.architects.orderService.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders/deliverer")
public class OrderControllerDeliverer {
    private final OrderServiceImpl orderService;

    @Autowired
    public OrderControllerDeliverer(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }


    @PutMapping("/update-status/{orderNumber}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse updateStatus(@PathVariable String orderNumber, @PathVariable String status, @RequestHeader("deliveryPersonId") Long deliveryPersonId){
        return orderService.updateStatusDeliverer(orderNumber, status, deliveryPersonId);
    }

    @GetMapping("/get-order/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrder(@PathVariable String orderNumber){
        return orderService.getOrder(orderNumber);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrdersResponse getOrders(){
        return orderService.getAllOrders();
    }
}
