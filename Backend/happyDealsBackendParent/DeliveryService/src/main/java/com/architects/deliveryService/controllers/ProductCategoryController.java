package com.architects.deliveryService.controllers;

import com.architects.deliveryService.entity.ProductCategory;
import com.architects.deliveryService.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productCategorys")

public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public String createProduct(@RequestBody ProductCategory productCategory){
        productCategoryService.saveProductCategory(productCategory);
        return "Product Category created successfully!";
    }

    // Update ProductCategory by productCategoryId
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestBody ProductCategory updatedProductCategory){
        productCategoryService.updateProductCategory(productId, updatedProductCategory);
        return "Product Category updated successfully!";
    }

    // Delete ProductCategory by productCategoryId
    @DeleteMapping("/{productCategoryId}")
    public String deleteProductCategory(@PathVariable Long productCategoryId){
        productCategoryService.deleteProductCategory(productCategoryId);
        return "Product Category deleted successfully!";
    }

    // Retrieve all ProductCategorys
    @GetMapping
    public List<ProductCategory> getAllProductCategorys(){
        return productCategoryService.getAllProductCategorys();
    }

    // Retrieve ProductCategory by productCategoryId
    @GetMapping("/{productCategoryId}")
    public ProductCategory getProductCategoryById(@PathVariable Long productCategoryId){
        return productCategoryService.getProductCategoryById(productCategoryId);
    }

}