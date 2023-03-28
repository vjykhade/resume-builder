package com.humancloud.resume.web.controller;

import com.humancloud.resume.web.dto.UserDTO;
import com.humancloud.resume.web.service.UserService;
import com.humancloud.resume.web.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO> registerUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.registerAccount(userDTO));
    }
}
