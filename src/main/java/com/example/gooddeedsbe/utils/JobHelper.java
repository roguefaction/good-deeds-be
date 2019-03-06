package com.example.gooddeedsbe.utils;

import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Job;

public class JobHelper {
    public static void validateJob(Job job) throws InvalidFieldException {
        JobValidator.validateTitle(job.getTitle());
        JobValidator.validateCity(job.getCity());
        JobValidator.validateContactPerson(job.getContactPerson());
        JobValidator.validatePhoneNumber(job.getPhoneNumber());
        JobValidator.validateEmail(job.getEmail());
        JobValidator.validateOrganization(job.getOrganization());
        JobValidator.validateDescription(job.getDescription());
        JobValidator.validateTags(job.getTags());
    }

    public static Job updateJob(Job oldJob, Job newJob){
        if(newJob.getTitle() != null)
            oldJob.setTitle(newJob.getTitle());
        if(newJob.getCity() != null)
            oldJob.setCity(newJob.getCity());
        if(newJob.getContactPerson() != null)
            oldJob.setContactPerson(newJob.getContactPerson());
        if(newJob.getPhoneNumber() != null)
            oldJob.setPhoneNumber(newJob.getPhoneNumber());
        if(newJob.getEmail() != null)
            oldJob.setEmail(newJob.getEmail());
        if(newJob.getOrganization() != null)
            oldJob.setOrganization(newJob.getOrganization());
        if(newJob.getDescription() != null)
            oldJob.setDescription(newJob.getDescription());
        if(newJob.getTags() != null)
            oldJob.setTags(newJob.getTags());
        return oldJob;
    }
}
