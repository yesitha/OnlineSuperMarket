package com.architects.inventoryService.dto.request;

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
    private String productQuantityAvailable;
    private String productImage;
    private BigDecimal productDiscount;
}
