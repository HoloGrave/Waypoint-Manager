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

    public static void addWaypoint(waypoint w){
        //add to database
    }
    public static void dropWaypoint(int index){
        //drop from database
    }
    public static void setIndex(int index){selectedRecord = index;}

    public static int getIndex(){return selectedRecord;}
    public static int getLastIndex(){
        int tSize = -1;
     try(Connection conn = getConnection()) {
         String query = "SELECT COALESCE( " +
                 "(SELECT MIN(w1.idwaypoint + 1) " +
                 "FROM waypoint w1 " +
                 "LEFT JOIN waypoint w2 " +
                 "ON w1.idwaypoint + 1 = w2.idwaypoint " +
                 "WHERE w2.idwaypoint IS NULL), " +
                 "(SELECT 1 + IFNULL(MAX(idwaypoint), 0) FROM waypoint)) AS next_id;";

         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery();

         if(rs.next()) {
             tSize = rs.getInt("next_id");
         }
     }
     catch (SQLException e){
         e.printStackTrace();
     }
     return tSize;
    }
    public static void editWaypoint(waypoint w, int index){
        //todo
    }
}
