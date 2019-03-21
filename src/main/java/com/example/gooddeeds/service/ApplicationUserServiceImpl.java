package com.example.gooddeeds.service;

import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import com.example.gooddeeds.repository.ApplicationUserRepository;
import com.example.gooddeeds.repository.DeedRepository;
import com.example.gooddeeds.utils.DeedSorter;
import com.example.gooddeeds.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.ws.http.HTTPException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;
    private DeedRepository deedRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository, DeedRepository deedRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.deedRepository = deedRepository;
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

    @Override
    public List<Deed> getUserDeedsByEmail(String email) {
        List<Deed> usersDeeds = deedRepository.findByApplicationUserOrderByDateAsc(applicationUserRepository.findByEmail(email));
        try{

            return DeedSorter.currentDateFilter(usersDeeds);

        } catch(ParseException ex){
            throw new HTTPException(405);
        }
    }

    @Override
    public List<Deed> getUserParticipationDeeds(String email) {
        ApplicationUser user = applicationUserRepository.findByEmail(email);
        Set<ApplicationUser> userSet = new HashSet<>();
        userSet.add(user);
        List<Deed> deeds = deedRepository.findAllByParticipatingUsersOrderByDateAsc(userSet);
        List<Deed> participationDeeds = new ArrayList<>();
        participationDeeds.addAll(deeds);
        try{
            return DeedSorter.currentDateFilter(participationDeeds);
        } catch (ParseException ex){
            return participationDeeds;
        }

    }


}
