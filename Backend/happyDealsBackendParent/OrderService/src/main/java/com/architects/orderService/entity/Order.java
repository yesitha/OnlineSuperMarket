package com.architects.orderService.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Indexed;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "CustomerOrder") // "order," which is a reserved keyword in SQL, is not a valid table name
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long customerId;
    private Date orderDate;
    @Column(unique = true)
    private String orderNumber;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal orderTotal;
    private String orderShippingAddress;
    private BigDecimal orderShippingCost = BigDecimal.ZERO;

    private Long deliverPersonId;
    private String deliveryPersonName;
    private String deliveryPersonPhoneNumber;
    private String deliveryPersonEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Cart")
    private Cart cart;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}
