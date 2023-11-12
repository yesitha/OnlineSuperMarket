package com.architects.inventoryService.services;


import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.ResponseProductDto;

import com.architects.inventoryService.dto.response.ProductDetailsDTO;

import com.architects.inventoryService.entity.Product;


import java.util.List;


public interface ProductService {
    public void  createProduct(RequestProductDto product);
    // Update Product by productId
    public void updateProduct(Long productId, RequestProductDto updatedProduct);

    // Delete Product by productId
    public void deleteProduct(Long productId);

    // Retrieve all Products
    public List<ResponseProductDto> getAllProducts();


    public Product getProductById(Long productId);

//    public Product getProductById(Long productId) {
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
//    }

//    public ProductDetailsDTO getProductDetailsById(Long productId) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
//        return ProductDetailsDTO.builder()
//                .productName(product.getProductName())
//                .productDescription(product.getProductDescription())
//                .productUnitPrice(product.getProductUnitPrice())
//                .productQuantityAvailable(product.getProductQuantityAvailable())
//                .productImage(product.getProductImage())
//                .productDiscount(product.getProductDiscount())
//                .build();
//    }

    

}