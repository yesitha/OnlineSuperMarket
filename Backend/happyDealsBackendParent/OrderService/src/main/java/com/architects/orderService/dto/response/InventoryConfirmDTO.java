package com.architects.orderService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryConfirmDTO {
    private Long productId;
    private BigDecimal quantityOrdered;
    private BigDecimal productUnitPrice;

    private String productName;
    private BigDecimal productDiscount;
}
