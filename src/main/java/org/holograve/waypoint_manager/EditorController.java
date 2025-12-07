package org.holograve.waypoint_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        ArrayList<Integer> errors = new ArrayList<>();
        if(nameField.getText().length() > 44) {errors.add(1);}
        if(descField.getText().length() > 254) {errors.add(2);}
        try{
            LocalDateTime curTime = LocalDateTime.now();
            waypoint w = new waypoint(AppState.getIndex(),nameField.getText(),descField.getText(),
                    Double.parseDouble(xCoordField.getText()),Double.parseDouble(yCoordField.getText()),Double.parseDouble(zCoordField.getText()),
                    curTime);
            AppState.editWaypoint(w);
        }
        catch (Exception e){
            System.out.println("Other Problem");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load the elements into the fields of what they were before
    }
}
