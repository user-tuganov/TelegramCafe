package ru.tuganov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tuganov.entity.order.CafeOrder;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser {
    @Id
    private Long id;
    @OneToOne
    private CafeAddress currentCafeAddress;
    @OneToMany(mappedBy = "telegramUser", cascade = CascadeType.ALL)
    private List<CafeOrder> cafeOrders;
}
