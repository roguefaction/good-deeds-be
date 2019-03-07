package com.example.gooddeedsbe.repository;

import com.example.gooddeedsbe.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    public List<Job> findAllByOrderByIdDesc();
}
