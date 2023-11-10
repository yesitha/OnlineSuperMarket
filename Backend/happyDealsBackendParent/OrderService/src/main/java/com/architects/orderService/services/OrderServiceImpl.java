package com.architects.orderService.services;

import com.architects.orderService.Repositories.OrderRepository;
import com.architects.orderService.entity.Order;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;


    public OrderServiceImpl(OrderRepository orderRepository, WebClient webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }

    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId) {


        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        if (order != null) {
            order.setDeliverPersonId(deliveryPersonId);
            orderRepository.save(order);
            UriTemplate uriTemplate = new UriTemplate("http://localhost:8081/api/v1/deliveryPersons/update-delivery-person-status/{deliveryPersonId}/{status}");
            URI uri = uriTemplate.expand(deliveryPersonId, false);

            webClient.put()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(Void.class)  // Assuming the response is empty
                    .block();


        }
    }


}
