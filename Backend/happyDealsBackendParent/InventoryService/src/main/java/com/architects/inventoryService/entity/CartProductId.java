package com.architects.inventoryService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "cart_id")
    private Long cartId;
}
