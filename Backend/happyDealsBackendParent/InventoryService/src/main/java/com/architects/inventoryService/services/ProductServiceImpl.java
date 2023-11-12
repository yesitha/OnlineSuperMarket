package com.architects.inventoryService.services;

import com.architects.inventoryService.Repositories.ProductRepository;
import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.ResponseInventoryKeeperDto;
import com.architects.inventoryService.dto.response.ResponseProductDto;
import com.architects.inventoryService.entity.InventoryKeeper;
import com.architects.inventoryService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    private final WebClient.Builder webClientBuilder;

    public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public void createProduct(RequestProductDto product) {
        UUID uuid = UUID.randomUUID();
        Long productID = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        Product p = new Product(
                productID,
                product.getProductName(),
                product.getProductDescription(),
                product.getProductUnitPrice(),
                product.getProductQuantityAvailable(),
                product.getProductImage(),
                product.getProductDiscount()

        );
        productRepository.save(p);
    }

    // Update Product by productId
    public void updateProduct(Long productId, RequestProductDto updatedProduct) {
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
    public List<ResponseProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ResponseProductDto(
                        p.getProductId(),
                        p.getProductName(),
                        p.getProductDescription(),
                        p.getProductUnitPrice(),
                        p.getProductQuantityAvailable(),
                        p.getProductImage(),
                        p.getProductDiscount()
                ))
                .collect(Collectors.toList());
    }

    // Retrieve Product by productId
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
    }

    public List<ResponseProductDto> getAllAvailableProducts() {
        return productRepository.findAll().stream()
                .filter(p -> p.getProductQuantityAvailable().compareTo(BigDecimal.ZERO) > 0)
                .map(p -> new ResponseProductDto(
                        p.getProductId(),
                        p.getProductName(),
                        p.getProductDescription(),
                        p.getProductUnitPrice(),
                        p.getProductQuantityAvailable(),
                        p.getProductImage(),
                        p.getProductDiscount()
                ))
                .collect(Collectors.toList());

}}
