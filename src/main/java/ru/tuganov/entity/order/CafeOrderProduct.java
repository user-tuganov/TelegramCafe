package ru.tuganov.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.tuganov.entity.Product;
import ru.tuganov.entity.Size;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CafeOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JsonIgnore
    private CafeOrderDiscount cafeOrderDiscount;
    @ManyToOne
    @JoinColumn(name = "cafe_order_id")
    @JsonIgnore
    private CafeOrder cafeOrder;
    @OneToMany(mappedBy = "cafeOrderProduct", cascade = CascadeType.ALL)
    private List<CafeOrderProductTopping> cafeOrderProductToppings;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
}
