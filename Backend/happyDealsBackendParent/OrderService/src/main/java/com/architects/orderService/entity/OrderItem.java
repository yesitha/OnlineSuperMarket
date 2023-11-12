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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long productId;
    private BigDecimal quantityOrdered;
    private String productName;
    private BigDecimal productUnitPrice;
    private BigDecimal productDiscount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
