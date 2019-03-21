package com.example.gooddeeds.service;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ApplicationUserService {

    void registerUser(ApplicationUser user);

    List<ApplicationUser> getAllUsers();

    ApplicationUser getUserByEmail(String email);

    List<Deed> getUserDeedsByEmail(String name);

    List<Deed> getUserParticipationDeeds(String email);
}
