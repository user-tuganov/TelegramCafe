package ru.tuganov.dto.order;

import java.util.List;

public record CafeOrderDto(
        long userId,
        String deliveryMethod,
        int totalPrice,
        long addressId,
        String orderDateTime,
        List<CafeOrderProductDto> products,
        List<CafeOrderDiscountDto> discounts
) {
}
