package com.humancloud.resume.web.config.filter;

import com.humancloud.resume.web.entity.Role;
import com.humancloud.resume.web.entity.Users;
import com.humancloud.resume.web.exception.BaseException;
import com.humancloud.resume.web.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Start actual authorization.");
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        Users user;
        try{
            user  = userRegistrationRepository.findByEmail(username);
        }
        catch (Exception e){
            throw new BaseException(String.valueOf(HttpStatus.UNAUTHORIZED.value()),"User not found");
        }

        final List<GrantedAuthority> authorities = getAuthorities(user.getRoles().stream().toList());
        final Authentication auth = new UsernamePasswordAuthenticationToken(username,password, authorities);

        log.info("End actual authorization.");
        return auth;
    }

    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List <GrantedAuthority> result = new ArrayList<>();
        Set<String> permissions = new HashSet<>();
        if(!ObjectUtils.isEmpty(roles)){
            roles.forEach(r ->{
                permissions.add(r.getName());
            });
        }
        permissions.forEach(p->{
            result.add(new SimpleGrantedAuthority(p));
        });
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
