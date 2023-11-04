package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name="deliveryPerson")
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    //@Column(name="DP_id")
    private String deliveryPersonId;
    private String deliveryPersonName;
    private String deliveryPersonPhoneNumber;
    private String deliveryPersonEmail;
    private String deliveryPersonPassword;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_DP_id",referencedColumnName ="deliveryPersonId")
    private List<Order> Order;
}
