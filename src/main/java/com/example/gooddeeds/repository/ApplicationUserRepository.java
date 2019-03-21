package com.example.gooddeeds.repository;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    ApplicationUser findByEmail(String email);
}