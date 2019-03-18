package com.example.gooddeeds.controller;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/info")
    public ApplicationUser getUserInfo(Authentication authentication){

        return applicationUserService.getUserByEmail(authentication.getName());
    }

}