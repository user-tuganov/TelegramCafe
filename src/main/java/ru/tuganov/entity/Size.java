package ru.tuganov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.tuganov.entity.order.CafeOrderProduct;

import java.util.List;

@Entity
@Setter
@Getter
public class Size {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private double price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
    @OneToMany(mappedBy = "size")
    @JsonIgnore
    private List<CafeOrderProduct> cafeOrderProduct;
}
