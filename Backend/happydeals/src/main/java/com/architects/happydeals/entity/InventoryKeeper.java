package com.architects.happydeals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InventoryKeeper {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long inventoryKeeperId;
    private String inventoryKeeperName;
    private String inventoryKeeperPhoneNumber;
    private String inventoryKeeperEmail;
    private String inventoryKeeperPassword;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_InventoryKeeper_Id", referencedColumnName = "inventoryKeeperId")
    private List<Product> Product;

}
