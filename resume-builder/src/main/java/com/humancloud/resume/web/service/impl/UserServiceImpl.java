package com.humancloud.resume.web.service.impl;

import com.humancloud.resume.web.dto.UserDTO;
import com.humancloud.resume.web.entity.Role;
import com.humancloud.resume.web.entity.Users;
import com.humancloud.resume.web.exception.BaseException;
import com.humancloud.resume.web.repository.RoleRepository;
import com.humancloud.resume.web.repository.UserRegistrationRepository;
import com.humancloud.resume.web.service.UserService;
import com.humancloud.resume.web.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponseDTO registerAccount(UserDTO userDTO) {
       BaseResponseDTO responseDTO = new BaseResponseDTO();
       validateAccount(userDTO);
       Users userRegistration = saveUserData(userDTO);

       try {
            userRegistrationRepository.save(userRegistration);
            responseDTO.setCode(String.valueOf(HttpStatus.OK.value()));
            responseDTO.setMessage("User account created successfully..");
       }
       catch (Exception e)
       {
            responseDTO.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
            responseDTO.setMessage("SERVICE UNAVAILABLE");
       }
       return null;
    }

    private Users saveUserData(UserDTO userDTO){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Users users = new Users();
        users.setEmail(userDTO.getEmail());
        users.setFullName(userDTO.getFullName());
        users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        users.setCreatedDate(simpleDateFormat.format(Date.from(Instant.now())));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(userDTO.getRole()));
        users.setRoles(roles);

        return users;

    }
    private void validateAccount(UserDTO userDTO){
        if(ObjectUtils.isEmpty(userDTO))
        {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Data must not empty");
        }

        Users userRegistration = userRegistrationRepository.findByEmail(userDTO.getEmail());
        System.out.println("userRegistration: "+userRegistration);
        if(!ObjectUtils.isEmpty(userRegistration)){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Email had Exists");
        }

        List <String> roles = roleRepository.findAll().stream().map(Role::getName).toList();
        if(!roles.contains(userDTO.getRole())){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Invalid Role");
        }
    }
}
