package com.architects.inventoryService.services;


import com.architects.inventoryService.dto.response.OrderDTO;
import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.OrderResponseDTO;
import com.architects.inventoryService.dto.response.ResponseProductDto;

import com.architects.inventoryService.dto.response.ProductDetailsDTO;


import java.util.List;


public interface ProductService {
    public void  createProduct(RequestProductDto product);
    // Update Product by productId
    public void updateProduct(Long productId, RequestProductDto updatedProduct);

    // Delete Product by productId
    public void deleteProduct(Long productId);

    // Retrieve all Products
    public List<ResponseProductDto> getAllProducts();
    public List<ResponseProductDto> getAllAvailableProducts();
    public ResponseProductDto getProductById(Long productId);
    public ProductDetailsDTO getProductDetailsById(Long productId);
    public OrderResponseDTO placeOrder(List<OrderDTO> products);
}