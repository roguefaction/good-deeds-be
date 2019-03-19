package com.example.gooddeeds.repository;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    ApplicationUser findByEmail(String email);
}