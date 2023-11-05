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
    private String cartId;
    private String cartName;


    @OneToMany(mappedBy = "cart")
    private Set<CartProduct> cartProducts = new HashSet<>();

}
