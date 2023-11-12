package com.architects.orderService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponseDTO {
    private Long productId;
    private String productName;
    private BigDecimal productUnitPrice;
    private BigDecimal quantityOrdered;
    private BigDecimal productDiscount;
    private BigDecimal totalPrice;

}
