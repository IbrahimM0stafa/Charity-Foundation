package com.example.projectdb;

public class Organization extends Donor
{
    private String type;
    private int numberOfEmployees;

    // Constructors
    public Organization() {
        super(); // Call the default constructor of the superclass (Donor)
    }

    public Organization(int id, String username, String name, String phoneNumber, String location, String password, String type, int numberOfEmployees) {
        super(id, username, name, phoneNumber, location, password); // Call the parameterized constructor of the superclass (Donor)
        this.type = type;
        this.numberOfEmployees = numberOfEmployees;
    }

    public Organization(int donorId, String username) {
    }

    // Getter and Setter methods for the 'type' attribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter methods for the 'numberOfEmployees' attribute
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

}