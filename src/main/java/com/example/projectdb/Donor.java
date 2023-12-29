package com.example.projectdb;

import java.sql.SQLException;

public class Donor {
    private int id;
    private String username;
    private String name;
    private String phoneNumber;
    private String location;
    private String password;

    // Constructors
    public Donor() {
    }

    public Donor(int id, String username, String name, String phoneNumber, String location, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.password = password;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static Donor signupcheck(String username, String name, String phoneNumber, String location, String password,String Job) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();
        return db.SignUp(username, name, phoneNumber,location,password,Job);
    }
    public static Donor Logincheck(String username, String password) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();
        return db.login(username,password);

    }
    public static Donor signupcheckOrg(String username, String name, String phoneNumber, String location, String password,String Job,int noofemployee) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();
        return db.SignUpOrg(username, name, phoneNumber,location,password,Job,noofemployee);
    }

}

