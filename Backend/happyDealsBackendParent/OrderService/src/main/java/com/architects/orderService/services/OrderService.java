package com.architects.orderService.services;

import com.architects.orderService.dto.request.OrderRequestDTO;
import com.architects.orderService.dto.response.OrderResponse;
import com.architects.orderService.dto.response.OrdersResponse;

public interface OrderService {

    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId);
    public OrderResponse placeOrder(Long customerId);
    public OrdersResponse getAllByCustomerId(Long customerId);
    public OrderResponse getOrder(String orderNumber, Long customerId);
    public OrderResponse cancelOrder(String orderNumber, Long customerId);

    //Admin
    public OrderResponse getOrder(String orderNumber);
    public OrdersResponse getAllOrders();
    public OrderResponse updateStatusAdmin(String orderNumber);
}
