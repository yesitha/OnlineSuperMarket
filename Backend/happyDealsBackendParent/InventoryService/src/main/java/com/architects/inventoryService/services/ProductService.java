package com.architects.inventoryService.services;

import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.ResponseProductDto;
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

    // Retrieve Product by productId
    public Product getProductById(Long productId);
}