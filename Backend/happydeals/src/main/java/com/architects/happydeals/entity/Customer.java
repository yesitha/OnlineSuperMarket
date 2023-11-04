package com.architects.happydeals.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter

public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private char[] customerPassword;
    private String customerPhoneNumber;
}
