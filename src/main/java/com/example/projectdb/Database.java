package com.example.projectdb;

import java.sql.*;

public class Database {

    String host = "sql11.freesqldatabase.com";
    String user = "sql11672372";
    String password = "Pppw7nl1rB";
    String dataBase = "sql11672372";

    private static Database database;
    public static Connection connection = null;

    private Database()throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + "/" + dataBase;
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
        catch (SQLException e){
            System.out.println("not connected + "+ e.getMessage());
        }
    }

    public static synchronized Database getInstance() throws SQLException, ClassNotFoundException {
        if(database == null){
            database = new Database();
        } else if (Database.getConnection().isClosed()) {
            database = new Database();
        }
        return database;
    }
    public static synchronized Connection getConnection() throws SQLException
    {
        return connection;
    }

    public Donor login(String username, String password) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = this.getConnection();

            // Check if the donor ID is present in the Individual table
            ps = c.prepareStatement("SELECT id FROM individual WHERE id IN (SELECT id FROM Donor WHERE USER_NAME = ? AND PASSWORD = ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Donor ID found in Individual table, assume it's an individual
                int donorId = rs.getInt("id");
                return new Individual(donorId, username);
            }

            // Check if the donor ID is present in the Organization table
            ps = c.prepareStatement("SELECT id FROM Organization WHERE id IN (SELECT id FROM Donor WHERE USER_NAME = ? AND PASSWORD = ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Donor ID found in Organization table, assume it's an organization
                int donorId = rs.getInt("id");
                return new Organization(donorId, username);
            }

            // Donor ID not found in either table
            throw new IllegalArgumentException("Invalid username or password.");
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

    public Donor SignUp(String username, String name, String phoneNumber, String location, String password, String job) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = this.getConnection();
            ps = c.prepareStatement("SELECT USER_NAME FROM Donor WHERE USER_NAME = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                throw new IllegalArgumentException("Username is already taken.");
            }


            ps = c.prepareStatement("INSERT INTO Donor(USER_NAME, name, PHONE_NUMBER, location, PASSWORD) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, phoneNumber);
            ps.setString(4, location);
            ps.setString(5, password);


            int rowsAffectedDonor = ps.executeUpdate();
            if (rowsAffectedDonor > 0) {
                System.out.println("Donor signed up successfully!");


                rs = ps.getGeneratedKeys();
                int donorId = -1;
                if (rs.next()) {
                    donorId = rs.getInt(1);


                    if (donorId != -1) {

                        String individualSql = "INSERT INTO individual(id, job) VALUES (?, ?)";
                        ps = c.prepareStatement(individualSql);

                        ps.setInt(1, donorId);
                        ps.setString(2, job);


                        int rowsAffectedIndividual = ps.executeUpdate();
                        if (rowsAffectedIndividual > 0) {
                            System.out.println("Individual data inserted successfully!");
                        } else {
                            System.out.println("Failed to insert data into the Individual table.");
                        }
                    }
                }
            } else {
                System.out.println("Failed to sign up donor.");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return null;
    }

    public Donor SignUpOrg(String username, String name, String phoneNumber, String location, String password, String type,int noofemp) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = this.getConnection();
            ps = c.prepareStatement("SELECT USER_NAME FROM Donor WHERE USER_NAME = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                throw new IllegalArgumentException("Username is already taken.");
            }


            ps = c.prepareStatement("INSERT INTO Donor(USER_NAME, name, PHONE_NUMBER, location, PASSWORD) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, phoneNumber);
            ps.setString(4, location);
            ps.setString(5, password);


            int rowsAffectedDonor = ps.executeUpdate();
            if (rowsAffectedDonor > 0) {
                System.out.println("Donor signed up successfully!");


                rs = ps.getGeneratedKeys();
                int donorId = -1;
                if (rs.next()) {
                    donorId = rs.getInt(1);


                    if (donorId != -1) {

                        String individualSql = "INSERT INTO Organization(id, Type,No_Of_Employees) VALUES (?, ?, ?)";
                        ps = c.prepareStatement(individualSql);

                        ps.setInt(1, donorId);
                        ps.setString(2, type);
                        ps.setInt(3, noofemp);


                        int rowsAffectedIndividual = ps.executeUpdate();
                        if (rowsAffectedIndividual > 0) {
                            System.out.println("Individual data inserted successfully!");
                        } else {
                            System.out.println("Failed to insert data into the Individual table.");
                        }
                    }
                }
            } else {
                System.out.println("Failed to sign up donor.");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return null;
    }
    public Resources AddDonation(String name , int amount) throws SQLException {
        Connection c = null;
        Resources resources;
        int availableAmount;
        try {
            c = this.getConnection();

            PreparedStatement selectStatement = c.prepareStatement("SELECT AMOUNT FROM Resources WHERE NAME = ?");
            selectStatement.setString(1,name);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                availableAmount = resultSet.getInt("AMOUNT");
                availableAmount+=amount;

                PreparedStatement updateStatement = c.prepareStatement("UPDATE Resources SET AMOUNT = ? WHERE NAME = ?");
                updateStatement.setInt(1, availableAmount);
                updateStatement.setString(2, name);
                resources = new Resources(availableAmount);
                updateStatement.executeUpdate();
            } else {
                throw new RuntimeException(" not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return null;
    }




}