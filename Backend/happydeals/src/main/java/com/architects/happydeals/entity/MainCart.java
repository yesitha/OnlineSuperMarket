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
    private String mainCartId;

    @ManyToMany
    private Set<Product> SelectedProducts= new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_CartID")
    private Cart cart;

    @OneToOne(mappedBy = "mainCart")
    private Order order;
}
