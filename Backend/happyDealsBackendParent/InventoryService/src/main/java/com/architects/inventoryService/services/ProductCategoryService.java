package com.architects.inventoryService.services;


import com.architects.inventoryService.dto.request.RequestProductCategoryDto;
import com.architects.inventoryService.dto.response.ResponseProductCategoryDto;

import java.util.List;

public interface ProductCategoryService {
    public void saveProductCategory(RequestProductCategoryDto productCategory);

    // Update productCategory by productCategoryId
    public void updateProductCategory(Long productCategoryId, RequestProductCategoryDto updatedProductCategory);


    // Delete productCategory by productCategory ID
    public void deleteProductCategory(Long productCategoryId);

    // Retrieve all productCategory
    public List<ResponseProductCategoryDto> getAllProductCategory();

    // Retrieve productCategory by productCategoryId
    public ResponseProductCategoryDto getProductCategoryById(Long productCategoryId);

    public ResponseProductCategoryDto getProductIdByName(String productCategoryName);

}