package com.architects.orderService.services;

public interface OrderService {

    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId);
}
