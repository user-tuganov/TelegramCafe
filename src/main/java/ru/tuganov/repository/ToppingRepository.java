package ru.tuganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tuganov.entity.Topping;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
    Topping findToppingById(Long id);
}
