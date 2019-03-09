package com.example.gooddeeds.controller;

import com.example.gooddeeds.exceptions.IdNotFoundException;
import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.model.Deed;
import com.example.gooddeeds.service.DeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "https://good-deeds-fe.herokuapp.com")
@RestController
public class DeedController {
    private DeedService deedService;

    @Autowired
    public DeedController(DeedService deedService) {
        this.deedService = deedService;
    }

    @GetMapping(value = "/deeds")
    public List<Deed> getAllJobs() {
        return deedService.getAllDeeds();
    }

    @GetMapping(value = "/deed/{id}")
    public Deed getDeedById(@PathVariable int id) {
        try {
            return deedService.getDeedById(id);
        } catch (IdNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Deed not found with given ID");
        }
    }

    @PostMapping(value = "/deed")
    public Deed createDeed(@RequestBody Deed deed) {
        try {
            return deedService.createDeed(deed);
        } catch (InvalidFieldException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping(value = "/deed/{id}")
    public Deed editDeed(@PathVariable int id, @RequestBody Deed deed) {
        try {
            return deedService.editDeed(id, deed);
        } catch (IdNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (InvalidFieldException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/deed/{id}")
    public void deleteJob(@PathVariable int id) {
        deedService.deleteDeed(id);
    }


}
