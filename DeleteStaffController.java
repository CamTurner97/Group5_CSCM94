package com.cafe.group5a2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DeleteStaffController {

    Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cafedb?user=root&password=");

    @FXML
    public Button ManagerHomeReturn;
    public Button OnSubmitButton;
    public TextField FirstName;
    public TextField LastName;

    public DeleteStaffController() throws SQLException {
    }

    @FXML
    public void onManagerHomeReturnClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ManagerHomeReturn.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("manager-view.fxml")));

            stage.setTitle("Manager");
            stage.getScene().setRoot(newRoot);
        } catch (IOException e) {
            System.out.println("Error loading page");
        }
    }

    public void onSubmitButtonClick(ActionEvent actionEvent) {
        String FirstName1 = FirstName.getText();
        String LastName1 = LastName.getText();

        String query = "DELETE FROM users WHERE f_name ='" + FirstName1 + "' AND l_name = '" + LastName1 + "';";
        try(Statement stmt = con.createStatement()) {
            stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error detected");
            e.printStackTrace();
        }
    }
}