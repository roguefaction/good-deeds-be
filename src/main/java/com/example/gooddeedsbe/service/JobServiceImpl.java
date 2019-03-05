package com.example.gooddeedsbe.service;

import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Job;
import com.example.gooddeedsbe.repository.JobRepository;
import com.example.gooddeedsbe.utils.JobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {


    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) throws InvalidFieldException {
        JobValidator.validateTitle(job.getTitle());
        JobValidator.validateCity(job.getCity());
        JobValidator.validateContactPerson(job.getContactPerson());
        JobValidator.validatePhoneNumber(job.getPhoneNumber());
        JobValidator.validateEmail(job.getEmail());
        JobValidator.validateOrganization(job.getOrganization());
        JobValidator.validateDescription(job.getDescription());
        JobValidator.validateTags(job.getTags());
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(int id) {
        jobRepository.deleteById(id);
    }
}
