package com.architects.inventoryService.services;

import com.architects.inventoryService.Repositories.ProductCategoryRepository;
import com.architects.inventoryService.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    private final WebClient.Builder webClientBuilder;

    public ProductCategoryService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public void saveProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    // Update ProductCategory by productCategoryId
    public void updateProductCategory(Long productCategoryId, ProductCategory updatedProductCategory) {
        ProductCategory existingProductCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new RuntimeException("Product Category not found with id " + productCategoryId));

        existingProductCategory.setProductCategoryName(updatedProductCategory.getProductCategoryName());

        productCategoryRepository.save(existingProductCategory);
    }

    // Delete ProductCategory by productCategoryId
    public void deleteProductCategory(Long productCategoryId) {
        productCategoryRepository.deleteById(productCategoryId);
    }

    // Retrieve all ProductCategorys
    public List<ProductCategory> getAllProductCategorys() {
        return productCategoryRepository.findAll();
    }

    // Retrieve ProductCategory by productCategoryId
    public ProductCategory getProductCategoryById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new RuntimeException("Product Category not found with id " + productCategoryId));
    }


    public ProductCategory getProductCategoryByCategoryName(String productCategoryName) {
        return productCategoryRepository.findProductCategoryByProductCategoryName(productCategoryName);
    }

}