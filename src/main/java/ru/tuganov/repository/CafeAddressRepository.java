package ru.tuganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tuganov.entity.CafeAddress;

@Repository
public interface CafeAddressRepository extends JpaRepository<CafeAddress, Long> {
    CafeAddress findCafeAddressById(Long id);
}
