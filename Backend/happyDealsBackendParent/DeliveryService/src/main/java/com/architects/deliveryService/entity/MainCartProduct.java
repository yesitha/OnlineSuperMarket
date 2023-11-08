package com.architects.deliveryService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainCartProduct implements Serializable {

    @EmbeddedId
    private MainCartProductId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("mainCartId")
    @JoinColumn(name = "main_cart_id")
    private MainCart mainCart;

    private int quantity;

    // Additional fields or methods can be added as needed
}
