package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tuganov.dto.ProductDto;
import ru.tuganov.entity.Product;
import ru.tuganov.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final ProductService productService;

    @GetMapping("/get-products")
    public ResponseEntity<List<ProductDto>> getMenu() {
        return ResponseEntity.ok(productService.getMenu());
    }

    @GetMapping("/get-products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
