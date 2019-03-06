package com.example.gooddeedsbe.service;

import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Job;
import com.example.gooddeedsbe.repository.JobRepository;
import com.example.gooddeedsbe.utils.JobHelper;
import com.example.gooddeedsbe.utils.JobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        JobHelper.validateJob(job);
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(int id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Job editJob(int id, Job newJob) throws InvalidFieldException {
        try{
            Job jobToUpdate = jobRepository.getOne(id);
            JobHelper.validateJob(newJob);
            return JobHelper.updateJob(jobToUpdate, newJob);
        } catch (EntityNotFoundException ex){
            throw new InvalidFieldException("Job not found with given ID");
        }
    }
}
