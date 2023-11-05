package com.architects.happydeals.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainCartProductId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "main_cart_id")
    private Long mainCartId;

    // Additional fields or methods can be added as needed
}

