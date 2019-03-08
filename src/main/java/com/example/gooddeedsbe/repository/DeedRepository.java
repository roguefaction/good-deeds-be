package com.example.gooddeedsbe.repository;

import com.example.gooddeedsbe.model.Deed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeedRepository extends JpaRepository<Deed, Integer> {
    public List<Deed> findAllByOrderByIdDesc();
}
