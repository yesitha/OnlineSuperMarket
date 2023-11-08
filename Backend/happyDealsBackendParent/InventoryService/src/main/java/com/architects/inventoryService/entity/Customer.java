package com.architects.inventoryService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id

    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long customerId;

    private String customerName;

    @Embedded
    private CustomerAddress customerAddress;

    private String customerEmail;
    private char[] customerPassword;
    private String customerPhoneNumber;
    private String designation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Customer_id", referencedColumnName = "customerId")
    private List<Order> Order;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Customer_id", referencedColumnName = "customerId")
    private List<Cart> Cart;

    @OneToOne(mappedBy = "customer")
    private MainCart mainCart;
}

@Embeddable
class CustomerAddress {
    private String street;
    private String city;
    private String district;
}
