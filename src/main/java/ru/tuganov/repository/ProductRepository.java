package ru.tuganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tuganov.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
}
