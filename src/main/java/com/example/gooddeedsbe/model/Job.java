package com.example.gooddeedsbe.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="Job")
public class Job implements Serializable {

    public Job() {
    }

    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(name = "name")
    private String Name;

    @Column(name = "city")
    private String City;

    @Column(name = "contact_peron")
    private String ContactPerson;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "email")
    private String Email;

    @Column(name = "organization")
    private String Organization;

    @Column(name = "max_people")
    private int MaxPeople;

    @Column(name = "current_people")
    private int CurrentPeople;


    @Column(name = "description")
    private String Description;


    @Column(name = "tags")
    private String Tags;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public int getMaxPeople() {
        return MaxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        MaxPeople = maxPeople;
    }

    public int getCurrentPeople() {
        return CurrentPeople;
    }

    public void setCurrentPeople(int currentPeople) {
        CurrentPeople = currentPeople;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }
}
