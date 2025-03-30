package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tuganov.dto.CafeEmployeeAuthDto;
import ru.tuganov.service.security.JwtService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CafeEmployeeAuthDto cafeEmployeeAuthDto) {
        return ResponseEntity.ok(jwtService.generateToken(cafeEmployeeAuthDto));
    }
}
