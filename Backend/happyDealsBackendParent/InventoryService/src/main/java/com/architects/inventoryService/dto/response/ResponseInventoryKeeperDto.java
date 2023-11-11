package com.architects.inventoryService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInventoryKeeperDto {
    private Long inventoryKeeperId;
    private String inventoryKeeperName;
    private String inventoryKeeperPhoneNumber;
    private String inventoryKeeperEmail;
    private String inventoryKeeperPassword;
}
