package com.architects.inventoryService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {
    private boolean success;
    private List<OrderConfirmDTO> products;
    private List<Long> failedProducts;
}
