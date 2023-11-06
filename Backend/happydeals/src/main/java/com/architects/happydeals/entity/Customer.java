package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private char[] customerPassword;
    private String customerPhoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Customer_id", referencedColumnName = "customerId")
    private List<Order> Order;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Customer_id", referencedColumnName = "customerId")
    private List<Cart> Cart;


}
