package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tuganov.dto.DiscountDto;
import ru.tuganov.entity.Discount;
import ru.tuganov.service.DiscountService;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
@Slf4j
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("/get-discounts")
    public ResponseEntity<List<DiscountDto>> getDiscounts() {
        return ResponseEntity.ok(discountService.getDiscounts());
    }

    @GetMapping("/get-discounts/{discountId}")
    public ResponseEntity<Discount> getDiscountDetails(@PathVariable long discountId) {
        return ResponseEntity.ok(discountService.getDiscount(discountId));
    }
}
