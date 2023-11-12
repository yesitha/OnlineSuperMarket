package com.architects.orderService.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDetailsDTO {
    private Long productId;
    private BigDecimal quantityAdded;
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private String productImage;
    private BigDecimal productDiscount;
}
