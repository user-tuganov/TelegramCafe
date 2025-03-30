package ru.tuganov.service.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class RolesGrantedAuthorityAdapter implements GrantedAuthority {
    private String role;
    @Override
    public String getAuthority() {
        return role;
    }
}
