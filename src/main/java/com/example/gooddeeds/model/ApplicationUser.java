package com.example.gooddeeds.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "application_user")
public class ApplicationUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "phone")
    private String phone;

    @Column (name = "name")
    private String name;

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
}
