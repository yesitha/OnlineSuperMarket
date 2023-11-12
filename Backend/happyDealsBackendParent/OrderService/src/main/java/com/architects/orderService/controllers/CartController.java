package com.architects.orderService.controllers;

import com.architects.orderService.dto.request.AddProductRequestDTO;
import com.architects.orderService.dto.request.CartItemDetailsDTO;
import com.architects.orderService.dto.request.ProductDetailsDTO;
import com.architects.orderService.dto.response.CartDetailsDTO;
import com.architects.orderService.services.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    @PostMapping ("/add-product-to-cart")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetailsDTO addProductToCart(@RequestBody AddProductRequestDTO addProductRequestDTO, @RequestHeader("customerId") Long customerId) {
        return cartService.addProductToCart(addProductRequestDTO, customerId);
    }

    //Remove a product from a cart
    @DeleteMapping("/remove-product-from-cart/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String removeProductFromCart(@RequestHeader("customerId") Long customerId, @PathVariable Long productId) {
        return ("Product with ID " + productId + " removed from cart with ID " + cartService.removeProductFromCart(customerId, productId));
    }

    //update a product quantity in a cart
    @PutMapping("/update-product-quantity-in-cart/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProductQuantityInCart(@RequestHeader("customerId") Long customerId, @PathVariable Long productId, @RequestParam("quantity") BigDecimal quantity) {
        return ("Product with ID " + productId + " updated quantity = " + cartService.updateProductQuantityInCart(customerId, productId, quantity));
    }

    //View all products in a cart
    @GetMapping("/view-products-in-cart")
    @ResponseStatus(HttpStatus.OK)
    public CartDetailsDTO viewProductsInCart(@RequestHeader("customerId") Long customerId) {
        return cartService.viewProductsInCart(customerId);
    }

    //Clear all products in a cart
    @DeleteMapping("/clear-all-products-in-cart")
    @ResponseStatus(HttpStatus.OK)
    public String clearProductsInCart(@RequestHeader("customerId") Long customerId) {
        return ("Cart with ID " + cartService.clearProductsInCart(customerId) + " cleared");
    }
}
