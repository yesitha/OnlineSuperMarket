package com.architects.happydeals.services;

import com.architects.happydeals.Repositories.ProductCategoryRepository;
import com.architects.happydeals.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

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
}