package com.onlinestoreapp.security;

import com.onlinestoreapp.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class SecurityAuthority implements GrantedAuthority {
    private final Role role;

    public SecurityAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}
