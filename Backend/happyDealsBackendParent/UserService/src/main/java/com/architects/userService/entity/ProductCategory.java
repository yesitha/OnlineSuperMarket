package com.architects.userService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long productCategoryId;
    private String productCategoryName;
}
