package com.humancloud.resume.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String getHome()
    {
        return "This is home";
    }

    @GetMapping("/admin")
    public String getAdmin()
    {
        return "This is admin";
    }
}
