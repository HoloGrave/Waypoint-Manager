package org.holograve.waypoint_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    @FXML private TextField descField;
    @FXML private Button editBtn;
    @FXML private TextField nameField;
    @FXML private TextField xCoordField;
    @FXML private TextField yCoordField;
    @FXML private TextField zCoordField;

    @FXML
    void editField(ActionEvent event) {
        //check for if the fields are invalid inputs
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load the elements into the fields of what they were before
    }
}
