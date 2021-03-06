package com.example.gooddeeds.controller;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import com.example.gooddeeds.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
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
    public ApplicationUser getUserInfo(Authentication authentication) {

        return applicationUserService.getUserByEmail(authentication.getName());
    }

    @GetMapping("/deeds")
    public List<Deed> getUserDeeds(Authentication authentication){
        return applicationUserService.getUserDeedsByEmail(authentication.getName());
    }


    @GetMapping("/participation-deeds")
    public List<Deed> getParticipationDeeds(Authentication authentication){
        return applicationUserService.getUserParticipationDeeds(authentication.getName());
    }

}