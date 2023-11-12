package com.architects.inventoryService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private BigDecimal productQuantityAvailable;
    private BigDecimal productDiscount;

    @ManyToOne
    @JoinColumn(name = "fk_ProductCategory_Id") // This should match the name in ProductCategory entity
    private ProductCategory productCategory;

}
