package com.example.projectdb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignUp_Org_Controller {

    @FXML
    private TextField LocTxt_SUp_Org;

    @FXML
    private TextField NameTxt_SUp_Org;

    @FXML
    private TextField NumOfEmploTxt_SUp_Org;

    @FXML
    private ComboBox<String> OrgTypeCmboBox;

    @FXML
    private TextField PassTxt_SUp_Org;

    @FXML
    private TextField PhNumTxt_SUp_Org;

    @FXML
    private TextField UserNameTxt_SUp_Org;

    // Constructor or other methods can be added here

    @FXML
    public void initialize() {
        // This method is automatically called after the FXML file has been loaded
        // It's a good place to set up initial state or load data
        OrgTypeCmboBox.getItems().addAll("Hotel", "Pharmacy", "Restaurant");
    }

    @FXML
    void LocTxt_SignUp_Org(ActionEvent event) {
        // Handle location text field action
    }

    @FXML
    void NameTxt_SignUp_Org(ActionEvent event) {
        // Handle name text field action
    }

    @FXML
    void NumOfEmpTxt_SignUp_Org(ActionEvent event) {
        // Handle number of employees text field action
    }

    @FXML
    void OrgTypeCmboBox_Signup_Org(ActionEvent event) {
        // Handle organization type combo box action
        String selectedOrgType = OrgTypeCmboBox.getValue();
        System.out.println("Selected Organization Type: " + selectedOrgType);
        // You can perform additional actions based on the selected organization type
    }

    @FXML
    void PNumTxt_SignUp_Org(ActionEvent event) {
        // Handle phone number text field action
    }

    @FXML
    void PassTxt_SignUp_Org(ActionEvent event) {
        // Handle password text field action
    }

    @FXML
    void SignUp_Btn_User(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Username = UserNameTxt_SUp_Org.getText();
        String Password = PassTxt_SUp_Org.getText();
        String PhoneNumber = PhNumTxt_SUp_Org.getText();
        String Location = LocTxt_SUp_Org.getText();
        String Name = NameTxt_SUp_Org.getText();
        String type = OrgTypeCmboBox.getValue();
        int NofEmployee = Integer.parseInt(NumOfEmploTxt_SUp_Org.getText());

        if (Username.isEmpty() || Password.isEmpty() || PhoneNumber.isEmpty() || Location.isEmpty()||Name.isEmpty()) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);
            Label contentLabel = new Label("Please fill the missing data.");
            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
            return;
        }
        Database db = Database.getInstance();
        try
        {
            Donor.signupcheckOrg(Username,Name,PhoneNumber,Location, Password,type,NofEmployee);
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);

            Label contentLabel = new Label("Registration done" );

            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

            dialog.showAndWait();
            //EventObject actionEvent = null;
            Node n = (Node) event.getSource();
            Stage closeWindow = (Stage) n.getScene().getWindow();
            closeWindow.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
            Object root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IllegalArgumentException e)
        {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);
            Label contentLabel = new Label("Username is already taken.");
            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void UserNameTxt_SignUp_Org(ActionEvent event) {
        // Handle username text field action
    }
}
