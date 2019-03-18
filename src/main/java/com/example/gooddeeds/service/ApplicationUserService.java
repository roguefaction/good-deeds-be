package com.example.gooddeeds.service;

import com.example.gooddeeds.model.ApplicationUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationUserService {

    void registerUser(ApplicationUser user);

    List<ApplicationUser> getAllUsers();
}
