package com.architects.inventoryService.services;

import com.architects.inventoryService.Repositories.ProductCategoryRepository;
import com.architects.inventoryService.dto.request.RequestProductCategoryDto;
import com.architects.inventoryService.dto.response.ResponseProductCategoryDto;
import com.architects.inventoryService.entity.ProductCategory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository, WebClient.Builder webClientBuilder){
        this.productCategoryRepository=productCategoryRepository;
        this.webClientBuilder = webClientBuilder;
    }


    public void saveProductCategory(RequestProductCategoryDto productCategory) {
        UUID uuid = UUID.randomUUID();
        Long productCategoryID = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        ProductCategory pc = new ProductCategory(
                productCategoryID,
                productCategory.getProductCategoryName()
        );
        productCategoryRepository.save(pc);
    }

    // Update Product by productId
    public void updateProductCategory(Long productCategoryId, RequestProductCategoryDto updatedProductCategory) {
        ProductCategory existingProductCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new RuntimeException("Inventory Keeper not found with id " + productCategoryId));

        existingProductCategory.setProductCategoryName(updatedProductCategory.getProductCategoryName());



        productCategoryRepository.save(existingProductCategory);
    }

    // Delete Inventory Keeper by Inventory Keeper ID
    public void deleteProductCategory(Long productCategoryId) {
        productCategoryRepository.deleteById(productCategoryId);
    }

    // Retrieve all Inventory Keepers
    public List<ResponseProductCategoryDto> getAllProductCategory() {
        return productCategoryRepository.findAll().stream()
                .map(ip -> new ResponseProductCategoryDto(
                        ip.getProductCategoryId(),
                        ip.getProductCategoryName()

                ))
                .collect(Collectors.toList());
    }

    // Retrieve Inventory Keeper by inventoryKeeperId
    public ResponseProductCategoryDto getProductCategoryById(Long productCategoryId) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(productCategoryId);
        if (productCategoryOptional.isPresent()) {
            return new ResponseProductCategoryDto(
                    productCategoryOptional.get().getProductCategoryId(),
                    productCategoryOptional.get().getProductCategoryName()
            );
        } else {
            throw new EntityNotFoundException("ProductCategory not found with ID: " + productCategoryId);
        }
    }

    // Retrieve product category Id by Category Name
    public ResponseProductCategoryDto getProductIdByName(String productCategoryName) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findByProductCategoryName(productCategoryName);
        if (productCategoryOptional.isPresent()) {
            return new ResponseProductCategoryDto(
                    productCategoryOptional.get().getProductCategoryId(),
                    productCategoryOptional.get().getProductCategoryName()
            );
        } else {
            throw new EntityNotFoundException("ProductCategoryId not found with Product Category Name: " + productCategoryName);
        }
    }


}
