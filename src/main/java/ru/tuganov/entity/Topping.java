package ru.tuganov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import ru.tuganov.entity.order.CafeOrderProductTopping;

import java.util.List;

@Entity
@Getter
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    @ManyToMany(mappedBy = "toppings")
    @JsonIgnore
    private List<Product> products;
    @OneToOne
    @JsonIgnore
    private CafeOrderProductTopping cafeOrderProductTopping;
}
