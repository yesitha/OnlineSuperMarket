package com.architects.inventoryService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductCategoryDto {
    private Long productCategoryId;
    private String productCategoryName;
}