package ru.tuganov.dto;

public record ProductDto(long id,
                         String name,
                         String imageURL,
                         String type,
                         double cheapestPrice) {
}
