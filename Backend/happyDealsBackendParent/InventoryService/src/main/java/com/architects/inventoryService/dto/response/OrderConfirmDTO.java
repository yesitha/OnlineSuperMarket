package com.architects.inventoryService.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class OrderConfirmDTO extends OrderDTO {
    private Long productId;
    private BigDecimal quantityOrdered;

    private BigDecimal productUnitPrice;

    private String productName;
    private BigDecimal productDiscount;

}
