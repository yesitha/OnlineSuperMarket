package com.architects.inventoryService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long productId;
    private BigDecimal quantityOrdered;
}
