package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tuganov.entity.CafeAddress;
import ru.tuganov.service.CafeAddressService;

import java.util.List;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {
    private final CafeAddressService cafeAddressService;

    @GetMapping("/cafe-addresses")
    public ResponseEntity<List<CafeAddress>> getCafeAddresses() {
        return ResponseEntity.ok(cafeAddressService.findAll());
    }
}
