package com.architects.orderService.dto.response;

import com.architects.orderService.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String orderNumber;
    private String orderShippingAddress;
    private OrderStatus orderStatus;
    private BigDecimal orderTotal;
    private BigDecimal orderShippingCost;
    private List<OrderItemResponseDTO> orderItems;
}
