package com.architects.inventoryService.services;

import com.architects.inventoryService.entity.Product;
import com.architects.inventoryService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final WebClient.Builder webClientBuilder;

    public ProductService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

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