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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mainCartId;

    @OneToMany(mappedBy = "mainCart")
    private Set<MainCartProduct> mainCartProducts = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_CartID")
    private Cart cart;

    @OneToOne(mappedBy = "mainCart")
    private Order order;
}
