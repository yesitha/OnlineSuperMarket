package com.architects.userService.entity;

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
}

@Embeddable
class CustomerAddress {
    private String street;
    private String city;
    private String district;
}
