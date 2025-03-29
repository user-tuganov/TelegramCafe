package ru.tuganov.dto.order;

import java.util.List;

public record CafeOrderDiscountDto(
        long id,
        int quantity,
        List<CafeOrderProductDto> cafeOrderProductDtoList
) {
}
