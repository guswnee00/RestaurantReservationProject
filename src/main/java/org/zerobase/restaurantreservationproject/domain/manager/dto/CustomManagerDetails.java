package org.zerobase.restaurantreservationproject.domain.manager.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomManagerDetails implements UserDetails {

    private final ManagerEntity managerEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return managerEntity.getRole();
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return managerEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return managerEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
