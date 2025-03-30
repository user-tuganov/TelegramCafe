package ru.tuganov.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tuganov.dto.CafeEmployeeAuthDto;
import ru.tuganov.entity.CafeEmployee;
import ru.tuganov.repository.CafeEmployeeRepository;

import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CafeEmployeeRepository cafeEmployeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public boolean validateCafeEmployee(CafeEmployeeAuthDto cafeEmployeeAuthDto) {
        UserDetails userDetails = loadUserByUsername(cafeEmployeeAuthDto.username());
        if (userDetails != null) {
            return bCryptPasswordEncoder.matches(
                    cafeEmployeeAuthDto.password(),
                    userDetails.getPassword());
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CafeEmployee> cafeEmployeeOptional = cafeEmployeeRepository.findByUsername(username);
        if (cafeEmployeeOptional.isPresent()) {
            return new CustomUserDetails(cafeEmployeeOptional.get());
        }
        return null;
    }
}
