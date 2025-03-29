package ru.tuganov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tuganov.dto.GetCafeAddressDto;
import ru.tuganov.dto.SetCafeAddressDto;
import ru.tuganov.entity.CafeAddress;
import ru.tuganov.entity.TelegramUser;
import ru.tuganov.repository.TelegramUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;
    private final CafeAddressService cafeAddressService;

    public void checkUser(long userId) {
        Optional<TelegramUser> optionalTelegramUser = telegramUserRepository.findById(userId);
        if (optionalTelegramUser.isEmpty()) {
            telegramUserRepository.save(new TelegramUser(userId, null, List.of()));
        }
    }

    public TelegramUser getTelegramUser(long userId) {
        return telegramUserRepository.findTelegramUserById(userId);
    }

    public void setAddress(SetCafeAddressDto setCafeAddressDto) {
        TelegramUser telegramUser = getTelegramUser(setCafeAddressDto.userId());
        CafeAddress cafeAddress = cafeAddressService.findCafe(setCafeAddressDto.cafeId());
        telegramUser.setCurrentCafeAddress(cafeAddress);
        telegramUserRepository.save(telegramUser);
    }

    public GetCafeAddressDto getAddress(long userId) {
        TelegramUser telegramUser = getTelegramUser(userId);
        CafeAddress cafeAddress = telegramUser.getCurrentCafeAddress();
        if (cafeAddress == null) {
            return null;
        }
        return new GetCafeAddressDto(cafeAddress.getId(), cafeAddress.getAddress());
    }

    public void saveTelegramUser(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }
}
