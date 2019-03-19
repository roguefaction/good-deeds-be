package com.example.gooddeeds.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "deed")
public class Deed implements Serializable {


    public static class Builder {

        private int id;

        private String title;
        private String city;
        private String date;
        private String contactPerson;
        private String phoneNumber;
        private String email;
        private String organization;
        private int maxPeople;
        private int currentPeople;
        private String description;
        private String tags;
        public Builder(int id) {
            this.id = id;
        }

        public Builder title(String title) {
            this.title = title;

            return this;
        }

        public Builder city(String city) {
            this.city = city;

            return this;
        }

        public Builder date(String date) {
            this.date = date;

            return this;
        }

        public Builder contactPerson(String contactPerson) {
            this.contactPerson = contactPerson;

            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;

            return this;
        }

        public Builder organization(String organization) {
            this.organization = organization;

            return this;
        }

        public Builder maxPeople(int maxPeople) {
            this.maxPeople = maxPeople;

            return this;
        }

        public Builder currentPeople(int currentPeople) {
            this.currentPeople = currentPeople;

            return this;
        }

        public Builder email(String email) {
            this.email = email;

            return this;
        }

        public Builder description(String description) {
            this.description = description;

            return this;
        }

        public Builder tags(String tags) {
            this.tags = tags;

            return this;
        }

        public Deed build() {
            Deed deed = new Deed();
            deed.city = this.city;
            deed.date = this.date;
            deed.contactPerson = this.contactPerson;
            deed.currentPeople = this.currentPeople;
            deed.description = this.description;
            deed.email = this.email;
            deed.id = this.id;
            deed.maxPeople = this.maxPeople;
            deed.organization = this.organization;
            deed.phoneNumber = this.phoneNumber;
            deed.tags = this.tags;
            deed.title = this.title;

            return deed;
        }


    }
    @Id
    @Column(name = "deed_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "city")
    private String city;

    @Column(name = "date")
    private String date;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "organization")
    private String organization;

    @Column(name = "max_people")
    private int maxPeople;

    @Column(name = "current_people")
    private int currentPeople;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "tags")
    private String tags;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ApplicationUser applicationUser;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "deeds_users",
            joinColumns = { @JoinColumn(name = "deed_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<ApplicationUser> participatingUsers = new HashSet<>();

    private Deed(){

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public int getCurrentPeople() {
        return currentPeople;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getDate() {
        return date;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Set<ApplicationUser> getParticipatingUsers() {
        return participatingUsers;
    }

    public void setParticipatingUsers(Set<ApplicationUser> participatingUsers) {
        this.participatingUsers = participatingUsers;
    }

}
