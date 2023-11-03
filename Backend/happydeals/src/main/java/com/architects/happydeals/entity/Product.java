package com.architects.happydeals.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private String productQuantityAvailable;
    private String productImage;
    private BigDecimal productDiscount;
}
