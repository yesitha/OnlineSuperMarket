package com.architects.orderService.services;

import com.architects.orderService.dto.request.OrderRequestDTO;
import com.architects.orderService.dto.response.OrderResponse;

public interface OrderService {

    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId);
    public OrderResponse placeOrder(Long customerId);
}
