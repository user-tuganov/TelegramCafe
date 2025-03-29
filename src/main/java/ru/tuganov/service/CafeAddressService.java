package ru.tuganov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tuganov.entity.CafeAddress;
import ru.tuganov.repository.CafeAddressRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeAddressService {
    private final CafeAddressRepository cafeAddressRepository;

    public List<CafeAddress> findAll() {
        return cafeAddressRepository.findAll();
    }

    public CafeAddress findCafe(long addressId) {
        return cafeAddressRepository.findById(addressId).orElse(null);
    }
}
