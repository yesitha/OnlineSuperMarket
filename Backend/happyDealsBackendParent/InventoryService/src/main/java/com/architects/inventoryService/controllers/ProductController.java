package com.architects.inventoryService.controllers;

import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.ResponseProductDto;

import com.architects.inventoryService.dto.response.ProductDetailsDTO;

import com.architects.inventoryService.entity.Product;
import com.architects.inventoryService.services.InventoryKeeperServiceImpl;
import com.architects.inventoryService.services.ProductServiceImpl;
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
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    // Retrieve productDetails by productId
    @GetMapping("/productDetails/{productId}")
    public ProductDetailsDTO getProductDetailsById(@PathVariable Long productId) {
        return productService.getProductDetailsById(productId);
    }

}