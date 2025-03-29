package ru.tuganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tuganov.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findDiscountById(Long id);
}
