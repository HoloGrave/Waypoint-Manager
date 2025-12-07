package org.holograve.waypoint_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateController {

    @FXML private Button createBtn;
    @FXML private TextField descField;
    @FXML private TextField nameField;
    @FXML private TextField xCoordField;
    @FXML private TextField yCoordField;
    @FXML private TextField zCoordField;

    @FXML
    void createRecord(ActionEvent event) {
        //check if if the inputs are valid and if so create the record, otherwise state the issue about the inputs
        ArrayList<Integer> errors = new ArrayList<>();
        if(nameField.getText().length() > 44) {errors.add(1);}
        if(descField.getText().length() > 254) {errors.add(2);}
        try{
            LocalDateTime curTime = LocalDateTime.now();
            waypoint w = new waypoint(AppState.getLastIndex(),nameField.getText(),descField.getText(),
                    Double.parseDouble(xCoordField.getText()),Double.parseDouble(yCoordField.getText()),Double.parseDouble(zCoordField.getText()),
                    curTime);
            AppState.addWaypoint(w);
        }
        catch (Exception e){
            System.out.println("Other Problem");
            e.printStackTrace();
        }
    }

}
