package com.architects.orderService.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryPersonDTO {
    private String deliveryPersonName;
    private String deliveryPersonPhoneNumber;
    private String deliveryPersonEmail;
}
