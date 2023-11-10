package com.architects.notificationService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long cartId;
    private String cartName;
}
