package ru.tuganov.service.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tuganov.entity.CafeEmployee;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {
    private final CafeEmployee cafeEmployee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new RolesGrantedAuthorityAdapter(cafeEmployee.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return cafeEmployee.getPassword();
    }

    @Override
    public String getUsername() {
        return cafeEmployee.getUsername();
    }
}
