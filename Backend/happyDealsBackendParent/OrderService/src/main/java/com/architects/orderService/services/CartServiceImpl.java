package com.architects.orderService.services;

import com.architects.orderService.Repositories.CartRepository;
import com.architects.orderService.entity.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService{
    
    private final CartRepository cartRepository;

    public Long createCart(Long customerId) {
        try {
            // Check if a cart already exists for the provided customerId
            if (cartRepository.existsByCustomerId(customerId)) {
                throw new IllegalStateException("A cart already exists for customer with ID: " + customerId);
            }

            // If no cart exists, create a new one
            Cart cart = Cart.builder()
                    .customerId(customerId)
                    .build();

            cartRepository.save(cart);
            log.info("Cart {} created successfully for customer {}", cart.getCartId(), cart.getCustomerId());
            return cart.getCartId();
        } catch (IllegalStateException e) {
            log.error("Failed to create cart for customer {}. Reason: {}", customerId, e.getMessage());
            throw e;
        }
    }


    public Long getCartByCustomerId(Long customerId) {
        try {
            // Check if a cart already exists for the provided customerId
            if (!cartRepository.existsByCustomerId(customerId)) {
                throw new IllegalStateException("No cart exists for customer with ID: " + customerId);
            }

            // If a cart exists, return the cartId
            Cart cart = cartRepository.findByCustomerId(customerId);
            log.info("Cart {} retrieved successfully for customer {}", cart.getCartId(), cart.getCustomerId());
            return cart.getCartId();
        } catch (IllegalStateException e) {
            log.error("Failed to retrieve cart for customer {}. Reason: {}", customerId, e.getMessage());
            throw e;
        }
    }
}
