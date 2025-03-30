package ru.tuganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tuganov.entity.CafeEmployee;

import java.util.Optional;

@Repository
public interface CafeEmployeeRepository extends JpaRepository<CafeEmployee, Long> {
    Optional<CafeEmployee> findByUsername(String username);
}
