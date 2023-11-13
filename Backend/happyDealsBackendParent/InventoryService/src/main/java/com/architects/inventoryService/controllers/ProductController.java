package com.architects.inventoryService.controllers;

import com.architects.inventoryService.dto.response.OrderDTO;
import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.OrderResponseDTO;
import com.architects.inventoryService.dto.response.ResponseProductDto;

import com.architects.inventoryService.dto.response.ProductDetailsDTO;

import com.architects.inventoryService.services.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    private  final ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService){
        this.productService=productService;
    }

    @PostMapping
    public String createProduct(@RequestBody RequestProductDto product) {
        productService.createProduct(product);
        return "Product created successfully!";
    }

    // Update Product by productId
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestBody RequestProductDto updatedProduct) {
        productService.updateProduct(productId, updatedProduct);
        return "Product updated successfully!";
    }

    @PutMapping("/{productId}/{updatedQuantity}")
    public String updateProductQuantity(@PathVariable Long productId,@PathVariable BigDecimal updatedQuantity) {
        productService.updateProductQuantity(productId, updatedQuantity);
        return "Product quantity updated successfully!";
    }

    // Delete Product by productId
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "Product deleted successfully!";
    }

    // Retrieve all Products
    @GetMapping
    public List<ResponseProductDto> getAllProducts() {
        return productService.getAllProducts();
    }



    // Retrieve Product by productId
    @GetMapping("/{productId}")
    public ResponseProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    // Retrieve productDetails by productId
    @GetMapping("/productDetails/{productId}")
    public ProductDetailsDTO getProductDetailsById(@PathVariable Long productId) {
        return productService.getProductDetailsById(productId);
    }

    @GetMapping("productname/{productName}")
    public List<ResponseProductDto> getProductByName(@PathVariable String productName) {
        return productService.getProductByName(productName);
    }

    @GetMapping("category/{fk_ProductCategory_Id}")
    public List<ResponseProductDto> getProductByCategoryId(@PathVariable Long fk_ProductCategory_Id) {
        return productService.getProductByCategoryId(fk_ProductCategory_Id);
    }

    @GetMapping("/availableProducts")
    public List<ResponseProductDto> getAllAvailableProducts() {
        return productService.getAllAvailableProducts();
    }

    @PutMapping("/place-order")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody List<OrderDTO> products){
        OrderResponseDTO response = productService.placeOrder(products);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cancel-order")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> cancelOrder(@RequestBody List<OrderDTO> products) {
        boolean result = productService.cancelOrder(products);
        if (!result) {
            return new ResponseEntity<>("Failed to cancel order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}