package com.architects.deliveryService.services;

import com.architects.deliveryService.Repositories.ProductRepository;
import com.architects.deliveryService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Update Product by productId
    public void updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductDescription(updatedProduct.getProductDescription());
        existingProduct.setProductUnitPrice(updatedProduct.getProductUnitPrice());
        existingProduct.setProductQuantityAvailable(updatedProduct.getProductQuantityAvailable());
        existingProduct.setProductImage(updatedProduct.getProductImage());
        existingProduct.setProductDiscount(updatedProduct.getProductDiscount());

        productRepository.save(existingProduct);
    }

    // Delete Product by productId
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // Retrieve all Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve Product by productId
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
    }
}