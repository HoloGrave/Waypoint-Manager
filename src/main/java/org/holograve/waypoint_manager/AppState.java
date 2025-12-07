package org.holograve.waypoint_manager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppState {
    private static final String URL = "jdbc:mysql://localhost:3306/waypointdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static int selectedRecord = -1;

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }

    public static List<waypoint> getWaypointList(){
        List<waypoint> waypoints = new ArrayList<>();
        try(Connection conn = getConnection())
        {
            String query = "SELECT * FROM waypoint";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                waypoints.add(new waypoint(rs.getInt("idwaypoint"),rs.getString("name"),rs.getString("description"),rs.getDouble("xcoord"),rs.getDouble("ycoord"),rs.getDouble("zcoord"), (LocalDateTime) rs.getObject("updatetime")));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return waypoints;
    }

    public static List<String> getWaypointListStrings(){
        return null;
    }

    public static void addWaypoint(waypoint w){
        //add to database
    }
    public static void dropWaypoint(int index){
        //drop from database
    }
    public static void setIndex(int index){selectedRecord = index;}

    public static int getIndex(){return selectedRecord;}
}
