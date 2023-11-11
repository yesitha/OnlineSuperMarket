package com.architects.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    private Long customerId;
    private Long productId;
    private int quantityAdded;

    @OneToOne(mappedBy = "cart")
    private Order order;
}
