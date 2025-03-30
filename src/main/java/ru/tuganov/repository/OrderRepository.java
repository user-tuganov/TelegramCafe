package ru.tuganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tuganov.entity.CafeAddress;
import ru.tuganov.entity.TelegramUser;
import ru.tuganov.entity.order.CafeOrder;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<CafeOrder, Long> {
    CafeOrder findCafeOrderById(Long orderId);
    @Query("select ord from CafeOrder ord where ord.cafeAddress = :cafe and ord.status in :statuses")
    List<CafeOrder> findCafeOrdersByCafeAddress(@Param("cafe") CafeAddress cafeAddress,
                                   @Param("statuses") Set<String> statuses);
    @Query("select ord from CafeOrder ord where ord.telegramUser = :telegramUser and ord.status in :statuses")
    List<CafeOrder> findCafeOrderByTelegramUser(@Param("telegramUser") TelegramUser telegramUser,
                                   @Param("statuses") Set<String> statuses);
}
