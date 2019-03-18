package com.example.gooddeeds.service;

import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.repository.ApplicationUserRepository;
import com.example.gooddeeds.utils.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
                                      BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void registerUser(ApplicationUser user) {
        try {
            UserValidator.validateUser(user);
        } catch (InvalidFieldException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    @Override
    public List<ApplicationUser> getAllUsers() {
        return applicationUserRepository.findAll();
    }

    @Override
    public ApplicationUser getUserByEmail(String email) {
        return applicationUserRepository.findByEmail(email);
    }
}
