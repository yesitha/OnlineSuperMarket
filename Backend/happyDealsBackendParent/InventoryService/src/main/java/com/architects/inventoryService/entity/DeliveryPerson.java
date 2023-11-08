package com.architects.inventoryService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Table(name="deliveryPerson")
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="DP_id")
    private Long deliveryPersonId;
    private String deliveryPersonName;
    private String deliveryPersonPhoneNumber;
    private String deliveryPersonEmail;
    private String deliveryPersonPassword;
    private boolean isAvailable = true;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_DP_id", referencedColumnName = "deliveryPersonId")
    private List<Order> Order;
}
