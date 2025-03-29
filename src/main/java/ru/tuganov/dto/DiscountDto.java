package ru.tuganov.dto;

public record DiscountDto(long id,
                          String name,
                          String description,
                          String imageURL,
                          double price) {
}
