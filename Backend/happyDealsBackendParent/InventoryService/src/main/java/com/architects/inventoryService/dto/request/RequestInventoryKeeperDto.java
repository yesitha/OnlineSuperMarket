package com.architects.inventoryService.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestInventoryKeeperDto {
    private String inventoryKeeperName;
    private String inventoryKeeperPhoneNumber;
    private String inventoryKeeperEmail;
    private String inventoryKeeperPassword;
}
