package com.architects.inventoryService.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class responseDeliveryPersonDto {

    private Long deliveryPersonId;
    private String deliveryPersonName;
    private String deliveryPersonPhoneNumber;
    private String deliveryPersonEmail;

}
