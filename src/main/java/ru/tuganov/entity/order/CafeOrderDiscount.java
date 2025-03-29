package ru.tuganov.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.tuganov.entity.Discount;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CafeOrderDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @OneToMany(mappedBy = "cafeOrderDiscount", cascade = CascadeType.ALL)
    private List<CafeOrderProduct> cafeOrderProducts;
    @ManyToOne
    @JoinColumn(name = "cafe_order_id")
    @JsonIgnore
    private CafeOrder cafeOrder;
}
