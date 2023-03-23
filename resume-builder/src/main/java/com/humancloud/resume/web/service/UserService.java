package com.humancloud.resume.web.service;

import com.humancloud.resume.web.entity.UserRegistration;
import com.humancloud.resume.web.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Service
public class UserService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    public String addUser(UserRegistration userRegistration)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        userRegistration.setCreatedDate(simpleDateFormat.format(Date.from(Instant.now())));
        UserRegistration userReg = userRegistrationRepository.findByEmail(userRegistration.getEmail());
        if(userReg == null)
        {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userRegistration.setPassword(bCryptPasswordEncoder.encode(userRegistration.getPassword()));
            userRegistrationRepository.save(userRegistration);
            return userRegistration.getFullName()+" added in database successfully";
        }
        else {
            return "User Email Id already exists in records, please check..!";
        }
    }
    public UserRegistration findUserByEmail(String email)
    {
        return userRegistrationRepository.findByEmail(email);
    }

}
