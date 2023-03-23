package com.humancloud.resume.web.repository;

import com.humancloud.resume.web.component.CustomUserDetails;
import com.humancloud.resume.web.entity.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        UserRegistration userRegistration = userRegistrationRepository.findByEmail(email);
        if(userRegistration == null)
        {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new CustomUserDetails(userRegistration);
    }
}
