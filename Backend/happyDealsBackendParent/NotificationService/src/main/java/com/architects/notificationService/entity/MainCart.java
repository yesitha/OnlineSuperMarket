package com.architects.notificationService.entity;

import jakarta.persistence.*;
import lombok.*;

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
