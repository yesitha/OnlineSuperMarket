package com.architects.inventoryService.controllers;

import com.architects.inventoryService.dto.request.RequestProductCategoryDto;
import com.architects.inventoryService.dto.response.ResponseProductCategoryDto;
import com.architects.inventoryService.entity.ProductCategory;
import com.architects.inventoryService.services.InventoryKeeperServiceImpl;
import com.architects.inventoryService.services.ProductCategoryService;
import com.architects.inventoryService.services.ProductCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productCategorys")

public class ProductCategoryController {

    private  final ProductCategoryServiceImpl productCategoryService;
    @Autowired
    public ProductCategoryController(ProductCategoryServiceImpl productCategoryService){
        this.productCategoryService=productCategoryService;
    }

    @PostMapping
    public String createProduct(@RequestBody RequestProductCategoryDto productCategory) {
        productCategoryService.saveProductCategory(productCategory);
        return "Product Category created successfully!";
    }

    // Update ProductCategory by productCategoryId
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestBody RequestProductCategoryDto updatedProductCategory) {
        productCategoryService.updateProductCategory(productId, updatedProductCategory);
        return "Product Category updated successfully!";
    }

    // Delete ProductCategory by productCategoryId
    @DeleteMapping("/{productCategoryId}")
    public String deleteProductCategory(@PathVariable Long productCategoryId) {
        productCategoryService.deleteProductCategory(productCategoryId);
        return "Product Category deleted successfully!";
    }

    // Retrieve all ProductCategorys
    @GetMapping
    public List<ResponseProductCategoryDto> getAllProductCategorys() {
        return productCategoryService.getAllProductCategory();
    }

    // Retrieve ProductCategory by productCategoryId
    @GetMapping("/{productCategoryId}")
    public ResponseProductCategoryDto getProductCategoryById(@PathVariable Long productCategoryId) {
        return productCategoryService.getProductCategoryById(productCategoryId);
    }





}