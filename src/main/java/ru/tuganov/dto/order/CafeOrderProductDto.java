package ru.tuganov.dto.order;

import java.util.List;

public record CafeOrderProductDto(
        String id,
        int quantity,
        long sizeId,
        List<Long> toppingsIds
) {
}
