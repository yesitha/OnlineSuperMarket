package com.architects.orderService.services;

import com.architects.orderService.Repositories.CartItemRepository;
import com.architects.orderService.Repositories.CartRepository;
import com.architects.orderService.dto.request.AddProductRequestDTO;
import com.architects.orderService.dto.request.ProductDetailsDTO;
import com.architects.orderService.dto.response.CartDetailsDTO;
import com.architects.orderService.dto.request.CartItemDetailsDTO;
import com.architects.orderService.entity.Cart;
import com.architects.orderService.entity.CartItem;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
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

    public ProductDetailsDTO addProductToCart(AddProductRequestDTO addProductRequestDTO, Long customerId) {
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

            // Check if the product with the specified productId already exists in the cart
            Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProductId().equals(addProductRequestDTO.getProductId()))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                // If the product already exists, update the quantity
                CartItem cartItem = existingCartItem.get();
                cartItem.setQuantityAdded(cartItem.getQuantityAdded().add(addProductRequestDTO.getQuantityAdded()));
            } else {
                // If the product does not exist, create a new CartItem
                CartItem carItem = CartItem.builder()
                        .cart(cart)
                        .productId(addProductRequestDTO.getProductId())
                        .quantityAdded(addProductRequestDTO.getQuantityAdded())
                        .productName(productDetailsDTO.getProductName())
                        .productDescription(productDetailsDTO.getProductDescription())
                        .productUnitPrice(productDetailsDTO.getProductUnitPrice())
                        .productImage(productDetailsDTO.getProductImage())
                        .productDiscount(productDetailsDTO.getProductDiscount())
                        .build();

                cart.getCartItems().add(carItem);
            }

            cartRepository.save(cart);
            log.info("Product {} added to cart {} successfully", addProductRequestDTO.getProductId(), cart.getCartId());
            return productDetailsDTO;
        } catch (IllegalStateException e) {
            log.error("Failed to add product {} to cart for customer {}. Reason: {}", addProductRequestDTO.getProductId(), customerId, e.getMessage());
            throw e;
        }
    }

    public String removeProductFromCart(Long customerId, Long productId){
        try {
            // Check if a cart already exists for the provided customerId
            if (!cartRepository.existsByCustomerId(customerId)) {
                throw new IllegalStateException("No cart exists for customer with ID: " + customerId);
            }

            // If a cart exists, remove the product from the cart
            Cart cart = cartRepository.findByCustomerId(customerId);

            // Check if the product with the specified productId exists in the cart
            Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                // If the product exists, remove it from the cartItems list
                cart.getCartItems().remove(existingCartItem.get());

                // Delete the CartItem entity using the repository
                cartItemRepository.deleteById(existingCartItem.get().getCartItemId());
            } else {
                // If the product does not exist, throw an exception
                throw new IllegalStateException("Product with ID " + productId + " does not exist in cart with ID " + cart.getCartId());
            }

            cartRepository.save(cart);
            log.info("Product {} removed from cart {} successfully", productId, cart.getCartId());
            return cart.getCartId().toString();
        } catch (IllegalStateException e) {
            log.error("Failed to remove product {} from cart for customer {}. Reason: {}", productId, customerId, e.getMessage());
            throw e;
        }
    }

    public CartDetailsDTO viewProductsInCart(Long customerId) {
        try {
            // Check if a cart already exists for the provided customerId
            if (!cartRepository.existsByCustomerId(customerId)) {
                throw new IllegalStateException("No cart exists for customer with ID: " + customerId);
            }

            // If a cart exists, return the cartItems
            Cart cart = cartRepository.findByCustomerId(customerId);
            log.info("Cart {} retrieved successfully for customer {}", cart.getCartId(), cart.getCustomerId());
            return CartDetailsDTO.builder()
                    .cartId(cart.getCartId())
                    .customerId(cart.getCustomerId())
                    .cartItems(cart.getCartItems().stream()
                            .map(cartItem -> CartItemDetailsDTO.builder()
                                    .productId(cartItem.getProductId())
                                    .productName(cartItem.getProductName())
                                    .productDescription(cartItem.getProductDescription())
                                    .productUnitPrice(cartItem.getProductUnitPrice())
                                    .productImage(cartItem.getProductImage())
                                    .productDiscount(cartItem.getProductDiscount())
                                    .quantityAdded(cartItem.getQuantityAdded())
                                    .build())
                            .toList())
                    .build();
        } catch (IllegalStateException e) {
            log.error("Failed to retrieve cart for customer {}. Reason: {}", customerId, e.getMessage());
            throw e;
        }
    }

    public BigDecimal updateProductQuantityInCart(Long customerId, Long productId, BigDecimal quantity) {
        try {
            // Check if a cart already exists for the provided customerId
            if (!cartRepository.existsByCustomerId(customerId)) {
                throw new EntityNotFoundException("No cart exists for customer with ID: " + customerId);
            }

            // If a cart exists, retrieve the cart
            Cart cart = cartRepository.findByCustomerId(customerId);

            // Check if the product with the specified productId exists in the cart
            Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                // If the product exists, update the quantity
                CartItem cartItem = existingCartItem.get();
                cartItem.setQuantityAdded(quantity);

                // Save the updated CartItem
                cartItemRepository.save(cartItem);

                // Check if the new quantity is zero, and if so, remove the product from the cart
                if (quantity.compareTo(BigDecimal.ZERO) == 0) {
                    removeProductFromCart(customerId, productId);
                }

                // Calculate and return the updated total quantity of the product in the cart

                return cart.getCartItems().stream()
                        .filter(item -> item.getProductId().equals(productId))
                        .map(CartItem::getQuantityAdded)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            } else {
                // If the product does not exist, throw an exception
                throw new EntityNotFoundException("Product with ID " + productId + " does not exist in cart with ID " + cart.getCartId());
            }
        } catch (EntityNotFoundException e) {
            // Handle the case where the cart or product is not found
            throw e;
        } catch (Exception e) {
            // Handle other exceptions
            throw new RuntimeException("Failed to update product quantity in cart. Reason: " + e.getMessage(), e);
        }
    }

    public String clearProductsInCart(Long customerId) {
        try {
            // Check if a cart already exists for the provided customerId
            if (!cartRepository.existsByCustomerId(customerId)) {
                throw new IllegalStateException("No cart exists for customer with ID: " + customerId);
            }

            // If a cart exists, clear the cartItems
            Cart cart = cartRepository.findByCustomerId(customerId);

            cart.getCartItems().clear();
            cartItemRepository.deleteAllByCart(cart);

            cartRepository.save(cart);

            log.info("Cart {} cleared successfully", cart.getCartId());
            return cart.getCartId().toString();
        } catch (IllegalStateException e) {
            log.error("Failed to clear cart for customer {}. Reason: {}", customerId, e.getMessage());
            throw e;
        }
    }
}
