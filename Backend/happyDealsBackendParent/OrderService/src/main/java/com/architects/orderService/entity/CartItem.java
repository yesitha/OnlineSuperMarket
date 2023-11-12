package com.architects.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Long productId;
    private BigDecimal quantityAdded;
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private String productImage;
    private BigDecimal productDiscount;
}
