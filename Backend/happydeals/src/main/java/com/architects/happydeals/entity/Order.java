package com.architects.happydeals.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "CustomerOrder") // "order," which is a reserved keyword in SQL, is not a valid table name
public class Order {
    @Id
    private String orderId;
    private Date orderDate;
    private String orderStatus;
    private BigDecimal orderTotal;
}
