package com.architects.orderService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class deliveryPersonAssignedEvent {
    private Long deliveryPersonId;
    private Long orderId;
}
