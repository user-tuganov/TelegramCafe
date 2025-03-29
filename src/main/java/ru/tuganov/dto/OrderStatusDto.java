package ru.tuganov.dto;

public record OrderStatusDto(long userId,
                             long orderId,
                             String status) {
}
