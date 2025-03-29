package ru.tuganov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tuganov.dto.ProductDto;
import ru.tuganov.entity.Product;
import ru.tuganov.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> getMenu() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getImageURL(),
                    product.getType(),
                    product.getSizes().getFirst().getPrice())
            );
        }
        return productDtos;
    }

    public Product getProduct(long id) {
        return productRepository.findProductById(id);
    }
}
