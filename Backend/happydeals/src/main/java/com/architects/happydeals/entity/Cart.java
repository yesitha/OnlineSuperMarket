package com.architects.happydeals.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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

    @ManyToMany
    private Set<Product> SelectedProducts= new HashSet<>();

    @OneToOne(mappedBy = "cart")
    private MainCart maincart;
}
