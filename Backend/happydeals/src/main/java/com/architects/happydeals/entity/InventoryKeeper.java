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

public class InventoryKeeper {
    @Id
    private String inventoryKeeperId;
    private String inventoryKeeperName;
    private String inventoryKeeperPhoneNumber;
    private String inventoryKeeperEmail;
    private String inventoryKeeperPassword;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_InventoryKeeper_Id", referencedColumnName = "inventoryKeeperId")
    private List<Product> Product;

}
