package ru.tuganov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<CafeOrder> cafeOrders;
}
