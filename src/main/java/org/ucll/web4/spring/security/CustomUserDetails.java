package org.ucll.web4.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.ucll.web4.entity.UserEntity;

import java.util.*;

public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;
    private Set<GrantedAuthority> grantedAuthorities;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
        grantedAuthorities = new HashSet<>(Collections.singletonList(new SimpleGrantedAuthority("User")));
    }

    public UUID getUserId() {
        return userEntity.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
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
