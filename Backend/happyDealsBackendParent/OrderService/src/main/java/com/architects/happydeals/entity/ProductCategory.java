package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductCategory {
    @Id
    private String productCategoryId;
    private String productCategoryName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_ProductCategory_Id", referencedColumnName = "productCategoryId")
    private List<Product> Product;
}
