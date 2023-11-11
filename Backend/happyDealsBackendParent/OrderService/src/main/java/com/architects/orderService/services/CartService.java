package com.architects.orderService.services;

public interface CartService {
    public Long createCart(Long customerId);

    public Long getCartByCustomerId(Long customerId);
}
