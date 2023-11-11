package com.architects.orderService.services;

import com.architects.orderService.dto.request.AddProductRequestDTO;

public interface CartService {
    public Long createCart(Long customerId);
    public Long getCartByCustomerId(Long customerId);
    public void addProductToCart(AddProductRequestDTO addProductRequestDTO, Long customerId);
}
