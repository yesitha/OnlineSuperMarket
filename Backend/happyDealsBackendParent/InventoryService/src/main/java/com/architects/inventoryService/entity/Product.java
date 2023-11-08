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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private String productQuantityAvailable;
    private String productImage;
    private BigDecimal productDiscount;


    @OneToMany(mappedBy = "product")
    private Set<CartProduct> cartProducts = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<MainCartProduct> mainCartProducts = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @ManyToMany(mappedBy = "Products")
    private Set<Order> orderSet = new HashSet<>();


}
