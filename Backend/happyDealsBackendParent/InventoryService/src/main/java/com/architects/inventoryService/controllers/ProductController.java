package com.architects.inventoryService.controllers;

import com.architects.inventoryService.entity.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.architects.inventoryService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public String createProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return "Product created successfully!";
    }

    // Update Product by productId
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct){
        productService.updateProduct(productId, updatedProduct);
        return "Product updated successfully!";
    }

    // Delete Product by productId
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return "Product deleted successfully!";
    }

    // Retrieve all Products
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // Retrieve Product by productId
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }

}