package com.architects.happydeals.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductCategory {
    @Id
    private String productCategoryId;
    private String productCategoryName;
}
