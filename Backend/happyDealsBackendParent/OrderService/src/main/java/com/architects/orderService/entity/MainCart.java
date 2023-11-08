package com.architects.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MainCart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long mainCartId;

    @OneToOne(mappedBy = "mainCart")
    private Order order;
}
