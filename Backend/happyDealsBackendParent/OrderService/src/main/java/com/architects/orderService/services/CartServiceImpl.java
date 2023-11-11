package com.architects.orderService.services;

import com.architects.orderService.Repositories.CartRepository;
import com.architects.orderService.dto.request.AddProductRequestDTO;
import com.architects.orderService.dto.request.ProductDetailsDTO;
import com.architects.orderService.entity.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final WebClient.Builder webClientBuilder;

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

    public void addProductToCart(AddProductRequestDTO addProductRequestDTO, Long customerId) {
        try {
            // Check if a cart already exists for the provided customerId
            if (!cartRepository.existsByCustomerId(customerId)) {
                throw new IllegalStateException("No cart exists for customer with ID: " + customerId);
            }

            // If a cart exists, add the product to the cart
            Cart cart = cartRepository.findByCustomerId(customerId);

            ProductDetailsDTO productDetailsDTO = webClientBuilder.build()
                    .get()
                    .uri("http://inventory-service/api/v1/products/productDetails/" + addProductRequestDTO.getProductId())
                    .retrieve()
                    .bodyToMono(ProductDetailsDTO.class)
                    .onErrorResume(e -> {
                        log.error("Failed to retrieve product details for product {}. Reason: {}", addProductRequestDTO.getProductId(), e.getMessage());
                        throw new IllegalStateException("Failed to retrieve product details for product " + addProductRequestDTO.getProductId());
                    })
                    .block();

            if (productDetailsDTO.getProductQuantityAvailable().compareTo(addProductRequestDTO.getQuantityAdded()) < 0) {
                throw new IllegalStateException("Quantity added exceeds quantity available for product " + addProductRequestDTO.getProductId());
            }

            cart.setProductId(addProductRequestDTO.getProductId());
            cart.setQuantityAdded(addProductRequestDTO.getQuantityAdded());

            cart.setProductName(productDetailsDTO.getProductName());
            cart.setProductDescription(productDetailsDTO.getProductDescription());
            cart.setProductUnitPrice(productDetailsDTO.getProductUnitPrice());
            cart.setProductQuantityAvailable(productDetailsDTO.getProductQuantityAvailable());
            cart.setProductImage(productDetailsDTO.getProductImage());
            cart.setProductDiscount(productDetailsDTO.getProductDiscount());

            cartRepository.save(cart);
            log.info("Product {} added to cart {} successfully", addProductRequestDTO.getProductId(), cart.getCartId());
        } catch (IllegalStateException e) {
            log.error("Failed to add product {} to cart for customer {}. Reason: {}", addProductRequestDTO.getProductId(), customerId, e.getMessage());
            throw e;
        }
    }
}
