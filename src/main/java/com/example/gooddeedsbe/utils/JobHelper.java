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
}
