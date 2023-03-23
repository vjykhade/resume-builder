package com.humancloud.resume.web.controller;

import com.humancloud.resume.web.entity.UserRegistration;
import com.humancloud.resume.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/createuser")
    public ResponseEntity<String> saveUserData(@RequestBody UserRegistration userRegistration)
    {
        UserRegistration userReg = userService.findUserByEmail(userRegistration.getEmail());
        if(userReg == null)
        {
            return new ResponseEntity<>(userService.addUser(userRegistration),HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
