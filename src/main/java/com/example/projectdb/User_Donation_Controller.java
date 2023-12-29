package com.example.projectdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class User_Donation_Controller {

    @FXML
    private Button DonateBtn_User_Donation;

    @FXML
    private TextField QuantityTxt_User_Donation;

    @FXML
    private Button SignUpVolBtn_User_Donation;

    @FXML
    private ComboBox<String> TypeCmboBox_User_Donation;

    @FXML
    public void initialize() {
        // This method is automatically called after the FXML file has been loaded
        // It's a good place to set up initial state or load data
        TypeCmboBox_User_Donation.getItems().addAll("Food", "Drugs", "Clothes","Funds");
    }

    @FXML
    void Donate_Btn_UserDonation(ActionEvent event) {

    }

    @FXML
    void Quantity_Txt_UserDonation(ActionEvent event) {

    }

    @FXML
    void SignUpVolBtn_UserDonation(ActionEvent event) {

    }

    @FXML
    void TypeCmboBox_UserDonation(ActionEvent event) {


    }

}
