package com.architects.orderService.services;

import com.architects.orderService.Repositories.OrderRepository;
import com.architects.orderService.entity.Cart;
import com.architects.orderService.entity.Order;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    public OrderServiceImpl(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;

        this.webClientBuilder = webClientBuilder;
    }

    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId) {


        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        if (order != null) {
            order.setDeliverPersonId(deliveryPersonId);
            orderRepository.save(order);


            UriTemplate uriTemplate = new UriTemplate("http://delivery-service/api/v1/deliveryPersons/update-delivery-person-status/{deliveryPersonId}/{status}");
            URI uri = uriTemplate.expand(deliveryPersonId, false);

            try {
                webClientBuilder.build().put()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(Void.class)  // Assuming the response is empty
                        .block();


            } catch (Exception e) {
                System.out.println(e);
            }


        }
    }

}
