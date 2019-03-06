package com.example.gooddeedsbe.service;

import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    List<Job> getAllJobs();

    Job createJob(Job job) throws InvalidFieldException;

    void deleteJob(int id);

    Job editJob(int id, Job job) throws InvalidFieldException;
}
