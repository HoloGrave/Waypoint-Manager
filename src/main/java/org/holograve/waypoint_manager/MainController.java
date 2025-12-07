package org.holograve.waypoint_manager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private Button createBtn;
    @FXML private Button editBtn;
    @FXML private ListView<waypoint> firstLV;

    @FXML
    void chngCreateView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("CreateFXML.fxml"));
        Stage createStage = new Stage();
        createStage.setScene(new Scene(fxmlLoader.load()));
        createStage.initModality(Modality.WINDOW_MODAL);
        createStage.initOwner(createBtn.getScene().getWindow());
        createStage.setTitle("Create Waypoint");

        createStage.showAndWait();
        this.firstLV.setItems(FXCollections.observableArrayList(AppState.getWaypointList()));
    }

    @FXML
    void chngEditView(ActionEvent event) throws IOException {
        if(firstLV.getSelectionModel().getSelectedIndex() > -1) {
            AppState.setIndex(firstLV.getSelectionModel().getSelectedIndex());
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("EditorFXML.fxml"));
            Stage editStage = new Stage();
            editStage.setScene(new Scene(fxmlLoader.load()));
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(editBtn.getScene().getWindow());
            editStage.setTitle("Edit Waypoint");

            editStage.showAndWait();
            this.firstLV.setItems(FXCollections.observableArrayList(AppState.getWaypointList()));
        }
        else {
            System.out.println("No character selected to open\nPlease select a character to open");
        }
    }

    @FXML
    void deleteValue(ActionEvent event) {
        //simply delete the value from the database and refresh the data
        int selectedItem = firstLV.getSelectionModel().getSelectedIndex();
        if(selectedItem > -1) {
            AppState.dropWaypoint(selectedItem);
            //refresh the list with new values
            this.firstLV.setItems(FXCollections.observableArrayList(AppState.getWaypointList()));
        }
        else{
            //message that tells the user that they dont have anything selected
            System.out.println("No data selected to delete");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load the database
        this.firstLV.setItems(FXCollections.observableArrayList(AppState.getWaypointList()));
    }

}

