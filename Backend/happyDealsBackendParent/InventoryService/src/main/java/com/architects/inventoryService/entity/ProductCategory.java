package com.architects.inventoryService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
//@AllArgsConstructor

@NoArgsConstructor

public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productCategoryId;
    private String productCategoryName;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_ProductCategory_Id", referencedColumnName = "productCategoryId")
//    private List<Product> Product;

    public ProductCategory(Long productCategoryId, String productCategoryName) {
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
    }


}
