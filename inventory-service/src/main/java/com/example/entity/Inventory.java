package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;  //product name
    private Integer quantity;
}
