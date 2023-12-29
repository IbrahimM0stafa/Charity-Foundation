package com.example.projectdb;

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

public class Login_Controller {


    @FXML
    private TextField Pass_Txt;

    @FXML
    private TextField UserName_Txt;

    @FXML
    private Button SignUp_Btn_Org;

    @FXML
    void Log_In(ActionEvent event) throws SQLException, ClassNotFoundException {
     String  username = UserName_Txt.getText();
     String  Password = Pass_Txt.getText();
        if (username.isEmpty() || Password.isEmpty() ) {
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
        try {
            Donor donor = Donor.Logincheck(username, Password);

            if (donor instanceof Individual) {
                // Open the individual form
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("User_Donation.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (donor instanceof Organization) {
                // Open the organization form
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Org_Donation.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setHeaderText(null);
                Label contentLabel = new Label("Wrong Username or Password.");
                VBox content = new VBox(contentLabel);
                dialog.getDialogPane().setContent(content);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();
                return;
            }

            // Close the login form
            Node n = (Node) event.getSource();
            Stage closeWindow = (Stage) n.getScene().getWindow();
            closeWindow.close();
        }
        catch (IllegalArgumentException e)
        {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);
            Label contentLabel = new Label("failed.");
            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Password(ActionEvent event) {

    }

    @FXML
    void Sign_up(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp_User.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Node n = (Node) event.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

    }

    @FXML
    void UserName(ActionEvent event) {

    }

    @FXML
    void SignUp_Org(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup_Org.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Node n = (Node) event.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

    }

}

