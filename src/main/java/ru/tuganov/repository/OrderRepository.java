package ru.tuganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tuganov.entity.order.CafeOrder;

@Repository
public interface OrderRepository extends JpaRepository<CafeOrder, Long> {
    CafeOrder findCafeOrderById(Long orderId);
}
