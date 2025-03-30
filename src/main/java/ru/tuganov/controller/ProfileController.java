package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tuganov.dto.GetCafeAddressDto;
import ru.tuganov.dto.SetCafeAddressDto;
import ru.tuganov.service.TelegramUserService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final TelegramUserService telegramUserService;

    @GetMapping("/get-address/{id}")
    public ResponseEntity<GetCafeAddressDto> getAddress(@PathVariable long id) {
        return ResponseEntity.ok(telegramUserService.getAddress(id));
    }

    @PostMapping("/set-address")
    public HttpStatus setAddress(@RequestBody SetCafeAddressDto setCafeAddressDto) {
        telegramUserService.setAddress(setCafeAddressDto);
        return HttpStatus.OK;
    }

    @PostMapping("/check-user/{userId}")
    public HttpStatus checkUser(@PathVariable long userId) {
        telegramUserService.checkUser(userId);
        return HttpStatus.OK;
    }
}
