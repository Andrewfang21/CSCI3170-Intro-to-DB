package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PassengerService {
    private Connection db;

    public PassengerService(Connection db) {
        this.db = db;
    }

    public void requestRide(
        int ID, int passengersCount, int minDrivingYears,
        String start, String destination, String model
    ) {
        try {
            PreparedStatement checkStmt;
            checkStmt = db.prepareStatement(
                "SELECT COUNT(*)\n" +
                "FROM drivers\n" +
                "JOIN vehicles ON drivers.vid = vehicles.vid\n" +
                "WHERE vehicles.model LIKE ? AND\n" +
                "vehicles.seats >= ? AND drivers.driving_years >= ?"  
            );
            checkStmt.setString(1, "%" + model + "%");
            checkStmt.setInt(2, passengersCount);
            checkStmt.setInt(3, minDrivingYears);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();

            int result = rs.getInt(1);
            
            if (result > 0) {
                PreparedStatement stmt = db.prepareStatement(
                    "INSERT INTO requests (pid, start_location, destination, model, passengers, taken, driving_years)\n" +
                    "VALUES  (?,?,?,?,?,?,?)" 
                );
                stmt.setInt(1, ID);
                stmt.setString(2, start);
                stmt.setString(3, destination);
                stmt.setString(4, model);
                stmt.setInt(5, passengersCount);
                stmt.setInt(6, 0);
                stmt.setInt(7, minDrivingYears);
                stmt.execute();
                
                System.out.println("Your request is placed. " + result + " drivers are able to take the request");
            } else {
                System.out.println("There are no drivers that fulfill your requests");
            }
        } catch (SQLException e){
            System.out.println("[ERROR] Error inserting table: " + e);
        }
    }

    public void checkTripRecords(int ID,
        String startDate, String endDate, String destination
    ) {}

    public boolean IDExists(int ID){
        try{
            PreparedStatement stmt = db.prepareStatement(
                "SELECT * FROM passengers WHERE pid = ?"
            );
            stmt.setInt(1, ID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() == false) {
                return false;
            }
            return true;
        } catch(SQLException e) {
            System.out.println("[Error] Error in finding ID: " + e);
            return false;
        }
    }

    public boolean locationExists (String location){
        try {
            PreparedStatement stmt = db.prepareStatement(
                "SELECT * FROM taxi_stops WHERE name = ?" 
            );
            stmt.setString(1, location);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() == false){
                return false;
            }
            return true;
        } catch(SQLException e) {
            System.out.println("[Error] Error in finding location: "+ e);
            return false;
        }
    }
}
