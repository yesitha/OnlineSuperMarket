package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MainCart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long mainCartId;

    @OneToMany(mappedBy = "mainCart")
    private Set<MainCartProduct> mainCartProducts = new HashSet<>();

    @OneToOne(mappedBy = "mainCart")
    private Order order;

    @OneToOne
    @JoinColumn(name = "customer_id") // Specify the foreign key column
    private Customer customer;
}
