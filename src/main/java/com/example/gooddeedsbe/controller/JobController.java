package com.example.gooddeedsbe.controller;

import com.example.gooddeedsbe.model.Job;
import com.example.gooddeedsbe.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/job")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping(value = "/job")
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @DeleteMapping(value = "/job/{id}")
    public void deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
    }


}
