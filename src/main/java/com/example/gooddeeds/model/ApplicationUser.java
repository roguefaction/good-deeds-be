package com.example.gooddeeds.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "application_user")
public class ApplicationUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "email", unique = true)
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "phone")
    private String phone;

    @Column (name = "name")
    private String name;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "participatingUsers")
    private Set<Deed> participatingDeeds = new HashSet<>();

    public ApplicationUser(String email, String password, String phone, String name) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
    }

    public ApplicationUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Deed> getParticipatingDeeds() {
        return participatingDeeds;
    }

    public void setParticipatingDeeds(Set<Deed> participatingDeeds) {
        this.participatingDeeds = participatingDeeds;
    }
}
