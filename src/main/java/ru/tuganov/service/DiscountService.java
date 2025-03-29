package ru.tuganov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tuganov.dto.DiscountDto;
import ru.tuganov.entity.Discount;
import ru.tuganov.repository.DiscountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<DiscountDto> getDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        List<DiscountDto> discountDtos = new ArrayList<>();
        for (Discount discount : discounts) {
            double price = discount.getProducts().stream()
                    .mapToDouble(product -> product.getSizes().getFirst().getPrice()).sum();
            price *= (1 - discount.getDiscountPercentage());
            discountDtos.add(new DiscountDto(
                    discount.getId(),
                    discount.getName(),
                    discount.getDescription(),
                    discount.getImageURL(),
                    price));
        }
        return discountDtos;
    }

    public Discount getDiscount(long discountId) {
        return discountRepository.findDiscountById(discountId);
    }
}
