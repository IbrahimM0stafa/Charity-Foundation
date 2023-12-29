package com.example.projectdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class User_Donation_Controller {

    @FXML
    private Button DonateBtn_User_Donation;

    @FXML
    private TextField QuantityTxt_User_Donation;

    @FXML
    private Button SignUpVolBtn_User_Donation;

    @FXML
    private ComboBox<String> TypeCmboBox_User_Donation;

    String selectedName;

    @FXML
    public void initialize() {

        TypeCmboBox_User_Donation.getItems().addAll("Food", "Medicine", "Clothes", "Funds");
        Resources resources = new Resources();
        TypeCmboBox_User_Donation.setOnAction(event -> {
            selectedName = TypeCmboBox_User_Donation.getSelectionModel().getSelectedItem();
        });

        DonateBtn_User_Donation.setOnAction(event -> {
            try {
                resources.updateresources(selectedName, Integer.parseInt(QuantityTxt_User_Donation.getText()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);

            Label contentLabel = new Label("Thanks for donation!");

            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

            dialog.showAndWait();
        });
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