package com.example.gooddeeds.controller;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import com.example.gooddeeds.service.ApplicationUserService;
import com.example.gooddeeds.service.DeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class DeedController {
    private DeedService deedService;
    private ApplicationUserService applicationUserService;

    @Autowired
    public DeedController(DeedService deedService, ApplicationUserService applicationUserService) {
        this.deedService = deedService;
        this.applicationUserService = applicationUserService;
    }

    @GetMapping(value = "/deeds")
    public List<Deed> getAllJobs() {
        return deedService.getAllDeeds();
    }

    @GetMapping(value = "/deed/{id}")
    public Deed getDeedById(@PathVariable int id) {
        return deedService.getDeedById(id);
    }

    @PostMapping(value = "/deed")
    public Deed createDeed(@RequestBody Deed deed, Authentication authentication) {
        ApplicationUser applicationUser = applicationUserService.getUserByEmail(authentication.getName());
        return deedService.createDeed(deed, applicationUser);
    }


    @PutMapping(value = "/deed/{id}")
    public Deed editDeed(@PathVariable int id, @RequestBody Deed deed, Authentication authentication) {
        ApplicationUser applicationUser = applicationUserService.getUserByEmail(authentication.getName());
        return deedService.editDeed(id, deed, applicationUser);
    }

    @DeleteMapping(value = "/deed/{id}")
    public HttpStatus deleteDeed(@PathVariable int id, Authentication authentication) {
        ApplicationUser applicationUser =  applicationUserService.getUserByEmail(authentication.getName());
        deedService.deleteDeed(id, applicationUser);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/upcomingdeeds")
    public List<Deed> getUpcommingDeeds() throws ParseException {
        return deedService.getUpcomingDeeds();
    }

    @GetMapping(value = "/allupcomingdeeds")
    public List<Deed> getAllUpcomingDeeds() throws ParseException {
        return deedService.getAllUpcomingDeeds();
    }

    @GetMapping(value = "/deed/{id}/participatingusers")
    public Set<ApplicationUser> getParticipatingUsersOfDeed(@PathVariable (name = "id") int deedID) throws ParseException {
        return deedService.getParticipatingUsersOfDeed(deedID);
    }

    @PostMapping(value = "/deed/{id}/participate")
    public void addParticipatingUser(@PathVariable (name = "id") int deedID, Authentication authentication){
        ApplicationUser applicationUser =  applicationUserService.getUserByEmail(authentication.getName());
        deedService.addParticipatingUser(deedID, applicationUser);
    }

    @DeleteMapping(value = "/deed/{id}/participate")
    public void removeParticipatingUser(@PathVariable (name = "id") int deedID, Authentication authentication){
        ApplicationUser applicationUser =  applicationUserService.getUserByEmail(authentication.getName());
        deedService.removeParticipatingUser(deedID, applicationUser);
    }

}
