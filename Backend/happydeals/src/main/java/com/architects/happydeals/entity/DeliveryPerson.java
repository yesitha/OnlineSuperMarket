package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name="deliveryPerson")
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="DP_id")
    private String deliveryPersonId;
    private String deliveryPersonName;
    private String deliveryPersonPhoneNumber;
    private String deliveryPersonEmail;
    private String deliveryPersonPassword;
    private boolean isAvailable = true;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_DP_id", referencedColumnName = "deliveryPersonId")
    private List<Order> Order;
}
