package com.example.projectdb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EventObject;

public class SignUp_User_Controller {

    @FXML
    private TextField LocTxt_SUp_User;

    @FXML
    private TextField NameTxt_SUp_User;

    @FXML
    private TextField PassTxt_SUp_User;

    @FXML
    private TextField PhNumTxt_SUp_User;

    @FXML
    private TextField UserNameTxt_SUp_User;

    @FXML
    private TextField Job_SUp_User;

    @FXML
    void LocTxt_SignUp_User(ActionEvent event) {

    }

    @FXML
    void NameTxt_SignUp_User(ActionEvent event) {

    }

    @FXML
    void PNumTxt_SignUp_User(ActionEvent event) {

    }

    @FXML
    void PassTxt_SignUp_User(ActionEvent event) {

    }

    @FXML
    void SignUp_Btn_User(ActionEvent event) throws SQLException, ClassNotFoundException {

        String Username = UserNameTxt_SUp_User.getText();
        String Password = PassTxt_SUp_User.getText();
        String PhoneNumber = PhNumTxt_SUp_User.getText();
        String Location = LocTxt_SUp_User.getText();
        String Name = NameTxt_SUp_User.getText();

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
            Donor.signupcheck(UserNameTxt_SUp_User.getText(),NameTxt_SUp_User.getText(),PhNumTxt_SUp_User.getText(),LocTxt_SUp_User.getText(), PassTxt_SUp_User.getText(),Job_SUp_User.getText());
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
    void UserNameTxt_SignUp_User(ActionEvent event) {

    }

    @FXML
    void Job_SignUp_User(ActionEvent event) {

    }
    

}
