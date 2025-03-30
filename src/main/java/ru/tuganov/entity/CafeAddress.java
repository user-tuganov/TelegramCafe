package ru.tuganov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import ru.tuganov.entity.order.CafeOrder;

import java.util.List;

@Entity
@Getter
public class CafeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    @OneToOne(mappedBy = "currentCafeAddress")
    @JsonIgnore
    private TelegramUser telegramUser;
    @OneToMany(mappedBy = "cafeAddress")
    @JsonIgnore
    private List<CafeOrder> cafeOrder;
    @OneToMany(mappedBy = "cafeAddress")
    @JsonIgnore
    private List<CafeEmployee> cafeEmployees;
}
