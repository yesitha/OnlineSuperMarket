package com.architects.notificationService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "CustomerOrder") // "order," which is a reserved keyword in SQL, is not a valid table name
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Date orderDate;
    private String orderStatus;
    private BigDecimal orderTotal;
    private String orderShippingAddress;
    private Date orderDeliveryDate;
    private BigDecimal orderShippingCost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Main_Cart")
    private MainCart mainCart;
}
