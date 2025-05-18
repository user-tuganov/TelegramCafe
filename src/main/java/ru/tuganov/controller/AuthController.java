package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tuganov.dto.CafeEmployeeAuthDto;
import ru.tuganov.service.security.JwtService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CafeEmployeeAuthDto cafeEmployeeAuthDto) {
        return ResponseEntity.ok(jwtService.generateToken(cafeEmployeeAuthDto));
    }
}
