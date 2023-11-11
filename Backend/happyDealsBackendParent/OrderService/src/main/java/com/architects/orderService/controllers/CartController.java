package com.architects.orderService.controllers;

import com.architects.orderService.dto.request.AddProductRequestDTO;
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
    @PostMapping("/create-cart")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCart(@RequestHeader("customerId") Long customerId) {
        return ("cartId = " + cartService.createCart(customerId));
    }

    //Get a cart by customer ID
    @GetMapping("/get-cart")
    @ResponseStatus(HttpStatus.OK)
    public String getCartByCustomerId(@RequestHeader("customerId") Long customerId) {
        return ("cartId = " + cartService.getCartByCustomerId(customerId));
    }

    //Add a product to a cart
    @PutMapping("/add-product-to-cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToCart(@RequestBody AddProductRequestDTO addProductRequestDTO, @RequestHeader Long customerId) {
        cartService.addProductToCart(addProductRequestDTO, customerId);
    }
}
