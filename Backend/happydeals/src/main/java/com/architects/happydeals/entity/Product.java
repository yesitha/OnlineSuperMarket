package com.architects.happydeals.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "SelectedProducts")
    private Set<Cart> cartSet = new HashSet<>();

    @ManyToMany(mappedBy = "SelectedProducts")
    private Set<MainCart> mainCartSet = new HashSet<>();

    @ManyToMany(mappedBy = "Products")
    private Set<Order> orderSet = new HashSet<>();


}
