package com.humancloud.resume.web.component;

import com.humancloud.resume.web.entity.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    @Autowired
    private UserRegistration userRegistration;

    public CustomUserDetails(UserRegistration userRegistration) {
        super();
        this.userRegistration = userRegistration;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userRegistration.getUserRole()));
    }

    @Override
    public String getPassword() {
        return userRegistration.getPassword();
    }

    @Override
    public String getUsername() {
        return userRegistration.getEmail();
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
