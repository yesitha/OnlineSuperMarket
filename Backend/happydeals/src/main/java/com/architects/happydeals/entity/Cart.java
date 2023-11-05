package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    private String cartName;


    @OneToMany(mappedBy = "cart")
    private Set<CartProduct> cartProducts = new HashSet<>();

    @OneToOne(mappedBy = "cart")
    private MainCart maincart;
}
