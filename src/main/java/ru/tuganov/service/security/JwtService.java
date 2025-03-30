package ru.tuganov.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tuganov.dto.CafeEmployeeAuthDto;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.access-expire}")
    private int accessExpire;
    private final CustomUserDetailsService customUserDetailsService;


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateToken(CafeEmployeeAuthDto cafeEmployeeAuthDto) {
        if (!customUserDetailsService.validateCafeEmployee(cafeEmployeeAuthDto)) {
            log.info("Invalid cafe employee");
            return "";
        }
        return Jwts.builder()
                .subject(cafeEmployeeAuthDto.username())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessExpire))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getPayload();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
