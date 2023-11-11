package com.architects.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Long customerId;
    //product details
    private Long productId;
    private BigDecimal quantityAdded =BigDecimal.ZERO;
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private BigDecimal productQuantityAvailable = BigDecimal.ZERO;
    private String productImage;
    private BigDecimal productDiscount;

    @OneToOne(mappedBy = "cart")
    private Order order;
}
