package ru.tuganov.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.tuganov.entity.Topping;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeOrderProductTopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_product_id")
    @JsonIgnore
    private CafeOrderProduct cafeOrderProduct;
    @ManyToOne
    @JoinColumn(name = "topping_id")
    private Topping topping;
}
