package com.architects.inventoryService.dto.request;

import com.architects.inventoryService.entity.ProductCategory;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private BigDecimal productQuantityAvailable= BigDecimal.ZERO; // Default value;
    private String productImage;
    private BigDecimal productDiscount;
    private ProductCategory productCategoryId;
}
