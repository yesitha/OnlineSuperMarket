package com.architects.inventoryService.services;


import com.architects.inventoryService.dto.response.OrderDTO;
import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.OrderResponseDTO;
import com.architects.inventoryService.dto.response.ResponseProductDto;

import com.architects.inventoryService.dto.response.ProductDetailsDTO;


import com.architects.inventoryService.entity.Product;
import org.springframework.web.bind.annotation.PathVariable;



import java.math.BigDecimal;
import java.util.List;


public interface ProductService {
    public void  createProduct(RequestProductDto product);
    // Update Product by productId
    public void updateProduct(Long productId, RequestProductDto updatedProduct);

    public void updateProductQuantity(Long productId, BigDecimal updatedQuantity);

    // Delete Product by productId
    public void deleteProduct(Long productId);

    // Retrieve all Products
    public List<ResponseProductDto> getAllProducts();
    public List<ResponseProductDto> getAllAvailableProducts();
    public ResponseProductDto getProductById(Long productId);
    public ProductDetailsDTO getProductDetailsById(Long productId);
    public OrderResponseDTO placeOrder(List<OrderDTO> products);
    public boolean cancelOrder(List<OrderDTO> products);


    public List<ResponseProductDto> getProductByName(String productName);

    public List<ResponseProductDto> getProductByCategoryId(Long fk_ProductCategory_Id);


}