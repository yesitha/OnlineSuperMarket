package com.architects.happydeals.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductId implements Serializable {

    @Column(name = "product_id")
    private String productId;

    @Column(name = "cart_id")
    private String cartId;
}
