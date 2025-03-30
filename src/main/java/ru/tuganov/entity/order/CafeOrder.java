package ru.tuganov.entity.order;

import jakarta.persistence.*;
import lombok.*;
import ru.tuganov.entity.CafeAddress;
import ru.tuganov.entity.TelegramUser;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int orderNumber;
    private String status;
    private String deliveryMethod;
    private int totalPrice;
    LocalDateTime orderDateTime;
    @ManyToOne
    @JoinColumn(name = "cafe_address_id")
    private CafeAddress cafeAddress;
    @ManyToOne
    private TelegramUser telegramUser;
    @OneToMany(mappedBy = "cafeOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CafeOrderProduct> products;
    @OneToMany(mappedBy = "cafeOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CafeOrderDiscount> discounts;
}
