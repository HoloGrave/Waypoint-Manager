module org.holograve.waypoint_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens org.holograve.waypoint_manager to javafx.fxml;
    exports org.holograve.waypoint_manager;
}