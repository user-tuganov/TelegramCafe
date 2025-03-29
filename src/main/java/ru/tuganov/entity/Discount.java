package ru.tuganov.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private double discountPercentage;
    @ManyToMany
    private List<Product> products;
//    @OneToMany
//    @JsonIgnore
//    private List<CafeOrderDiscount> cafeOrderDiscount;
}
