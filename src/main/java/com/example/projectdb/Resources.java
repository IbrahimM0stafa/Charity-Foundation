package com.example.projectdb;

import java.sql.SQLException;

public class Resources {
    private int id;
    private String name;
    private int amount;
    private String foundationName;

    // Constructor
    public Resources(int id, String name, int amount, String foundationName) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.foundationName = foundationName;
    }

    public Resources(int amount) {
    }

    public Resources() {

    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getFoundationName() {
        return foundationName;
    }

    // Setter methods (if needed)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFoundationName(String foundationName) {
        this.foundationName = foundationName;
    }
    public static Resources updateresources (String name , int amount ) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();
        return db.AddDonation(name, amount);
    }
}