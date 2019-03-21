package com.example.gooddeeds.repository;

import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DeedRepository extends JpaRepository<Deed, Integer> {
    public List<Deed> findAllByOrderByIdDesc();
    public List<Deed> findAllByOrderByDateAsc();
    public List<Deed> findByApplicationUserOrderByDateAsc(ApplicationUser applicationUser);
    public List<Deed> findAllByParticipatingUsersOrderByDateAsc(Set<ApplicationUser> participatingUser);
}
