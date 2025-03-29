package ru.tuganov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tuganov.entity.Topping;
import ru.tuganov.repository.ToppingRepository;

@Service
@RequiredArgsConstructor
public class ToppingService {
    private final ToppingRepository toppingRepository;

    public Topping getTopping(long toppingId) {
        return toppingRepository.findToppingById(toppingId);
    }
}
