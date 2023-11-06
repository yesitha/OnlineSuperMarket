package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "CustomerOrder") // "order," which is a reserved keyword in SQL, is not a valid table name
public class Order {
    @Id
    private String orderId;
    private Date orderDate;
    private String orderStatus;
    private BigDecimal orderTotal;

    @ManyToMany
    private Set<Product> Products = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Main_Cart")
    private MainCart mainCart;
}
