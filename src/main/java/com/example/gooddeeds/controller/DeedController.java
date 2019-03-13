package com.example.gooddeeds.controller;

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

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*")
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
        return deedService.getDeedById(id);
    }

    @PostMapping(value = "/deed")
    public Deed createDeed(@RequestBody Deed deed) {
        return deedService.createDeed(deed);
    }

    @PutMapping(value = "/deed/{id}")
    public Deed editDeed(@PathVariable int id, @RequestBody Deed deed) {
        return deedService.editDeed(id, deed);
    }

    @DeleteMapping(value = "/deed/{id}")
    public HttpStatus deleteDeed(@PathVariable int id) {
        deedService.deleteDeed(id);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/upcomingdeeds")
    public List<Deed> getUpcommingDeeds() throws ParseException {
        return deedService.getUpcomingDeeds();
    }

}
