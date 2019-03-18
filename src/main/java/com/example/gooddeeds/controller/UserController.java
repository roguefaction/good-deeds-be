package com.example.gooddeeds.controller;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {


    private ApplicationUserService applicationUserService;

    @Autowired
    public UserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/register")
    public void signUp(@RequestBody ApplicationUser user) {
        applicationUserService.registerUser(user);
    }
}