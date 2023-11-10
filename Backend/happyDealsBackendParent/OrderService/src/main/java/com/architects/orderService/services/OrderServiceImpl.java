package com.architects.orderService.services;

import com.architects.orderService.Repositories.OrderRepository;
import com.architects.orderService.entity.Order;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderReposoitory;
    private final WebClient webClient;


    public OrderServiceImpl(OrderRepository orderReposoitory, WebClient webClient) {
        this.orderReposoitory = orderReposoitory;
        this.webClient = webClient;
    }

    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId) {

        Order order = orderReposoitory.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        if (order != null) {
            order.setDeliverPersonId(deliveryPersonId);
            orderReposoitory.save(order);
            webClient.put()
                    .uri(builder -> builder.path("http://localhost:8081/api/v1/deliveryPersons/update-delivery-person-status/{deliveryPersonId}/{status}")
                            .build(deliveryPersonId, false));

        }
    }



}
