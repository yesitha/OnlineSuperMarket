package com.architects.orderService.controllers;

import com.architects.orderService.services.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;

    //Create a cart for a customer
    @PostMapping("/create-cart/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCart(@PathVariable Long customerId) {
        return ("cartId = " + cartService.createCart(customerId));
    }

    //Get a cart by customer ID
    @GetMapping("/get-cart/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public String getCartByCustomerId(@PathVariable Long customerId) {
        return ("cartId = " + cartService.getCartByCustomerId(customerId));
    }

    //Add a product to a cart
    @PostMapping("/add-product-to-cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToCart(Long productId, Long cartId) {
//        cartService.addProductToCart(productId, cartId);
    }
}
