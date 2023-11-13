package com.architects.orderService.controllers;

import com.architects.orderService.dto.response.OrderResponse;
import com.architects.orderService.dto.response.OrdersResponse;
import com.architects.orderService.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders/admin")
public class OrderControllerAdmin {
    private final OrderServiceImpl orderService;

    @Autowired
    public OrderControllerAdmin(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get-order/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrder(@PathVariable String orderNumber){
        return orderService.getOrder(orderNumber);
    }

    @GetMapping("/get-orders")
    @ResponseStatus(HttpStatus.OK)
    public OrdersResponse getOrders(){
        return orderService.getAllOrders();
    }

    @PutMapping("/process/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse updateStatus(@PathVariable String orderNumber){
        return orderService.updateStatusAdmin(orderNumber);
    }

}
