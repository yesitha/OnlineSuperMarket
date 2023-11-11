package com.architects.orderService.services;

import com.architects.orderService.dto.request.AddProductRequestDTO;
import com.architects.orderService.dto.request.ProductDetailsDTO;
import com.architects.orderService.dto.response.CartDetailsDTO;

public interface CartService {
    public Long createCart(Long customerId);
    public Long getCartByCustomerId(Long customerId);
    public ProductDetailsDTO addProductToCart(AddProductRequestDTO addProductRequestDTO, Long customerId);
    public String removeProductFromCart(Long customerId, Long productId);
    public CartDetailsDTO viewProductsInCart(Long customerId);
}
