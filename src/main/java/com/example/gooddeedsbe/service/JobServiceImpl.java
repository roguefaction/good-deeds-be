package com.example.gooddeedsbe.service;

import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Job;
import com.example.gooddeedsbe.repository.JobRepository;
import com.example.gooddeedsbe.utils.JobHelper;
import com.example.gooddeedsbe.utils.JobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
        Optional<Job> jobToUpdate = jobRepository.findById(id);
        if(jobToUpdate.isPresent()){
            JobHelper.validateJob(newJob);
            newJob.setId(id);
            return jobRepository.save(newJob);
        } else {
            throw new InvalidFieldException("Job not found with given ID");
        }

    }
}
