package com.example.projectdb;

public class Individual extends Donor {

    private String job;

    // Constructors
    public Individual() {
        super(); // Call the default constructor of the superclass (Donor)
    }



    public Individual(int id, String username, String name, String phoneNumber, String location, String password, String job) {
        super(id, username, name, phoneNumber, location, password); // Call the parameterized constructor of the superclass (Donor)
        this.job = job;
    }

    public Individual(int donorId, String username) {
    }

    // Getter and Setter methods for the 'job' attribute
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}