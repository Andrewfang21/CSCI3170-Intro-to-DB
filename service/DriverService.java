package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import models.Trip;

public class DriverService {
    private Connection db;
    private Trip trip;

    public DriverService(Connection db, Trip trip) {
        this.db = db;
        this.trip = trip;
    }

    public void searchRequests(
        int driverID, int locationX, int locationY, int maxDistance
    ) {
        try {
            PreparedStatement stmt = db.prepareStatement(
                "SELECT R.rid, P.name, R.passengers, R.start_location, R.destination\n" +
                "FROM requests AS R, passengers AS P\n" +
                "WHERE\n" +
                "   P.pid = R.pid AND\n" +
                "   R.taken = FALSE AND (\n" +
                "       SELECT model\n" +
                "       FROM drivers, vehicles\n" +
                "       WHERE\n" +
                "           drivers.vid = vehicles.vid AND\n" +
                "           drivers.did = ? AND\n" +
                "           vehicles.seats >= R.passengers\n" +
                "       LIMIT 1\n" +
                "   ) LIKE CONCAT('%', R.model, '%') AND\n" +
                "   R.driving_years <= (\n" +
                "       SELECT driving_years\n" +
                "       FROM drivers\n" +
                "       WHERE drivers.did = ?\n" +
                "   ) AND (\n" +
                "       SELECT ABS(loc_x - ?) + ABS(loc_y - ?)\n" +
                "       FROM taxi_stops\n" +
                "       WHERE\n" +
                "           name = R.start_location\n" +
                "   ) <= ?"
            );

            stmt.setInt(1, driverID);
            stmt.setInt(2, driverID);
            stmt.setInt(3, locationX);
            stmt.setInt(4, locationY);
            stmt.setInt(5, maxDistance);
            
            ResultSet rs = stmt.executeQuery();

            System.out.println("request ID, passenger name, num of passengers, start location, destination");
            while (rs.next()) {
                System.out.printf(
                    "%d, %s, %d, %s, %s\n",
                    rs.getInt("rid"),
                    rs.getString("name"),
                    rs.getInt("passengers"),
                    rs.getString("start_location"), rs.getString("destination")
                );
            }
    
        } catch (SQLException e) {
            System.out.println("[ERROR] Searching requests: " + e);
            e.printStackTrace();
        }
    }

    public void takeRequest(int driverID, int requestID) {
        boolean isVacant = isVacant(driverID);
        if (!isVacant) {
            System.out.println("[ERROR] Driver has an unfinished trip");
            return;
        }

        try {
            PreparedStatement stmt = db.prepareStatement(
                "SELECT *\n" +
                "FROM requests\n" +
                "WHERE\n" +
                "   taken = FALSE AND\n" +
                "   passengers <= (\n" +
                "       SELECT seats\n" +
                "       FROM vehicles, drivers\n" +
                "       WHERE\n" +
                "           vehicles.vid = drivers.vid AND\n" +
                "           drivers.did = ?\n" +
                "       LIMIT 1\n" +
                "   ) AND\n" +
                "   driving_years <= (\n" +
                "       SELECT driving_years\n" +
                "       FROM drivers\n" +
                "       WHERE did = ?\n" +
                "       LIMIT 1\n" +
                "   ) AND\n" +
                "   rid = ?\n" +
                "LIMIT 1"
            );
            stmt.setInt(1, driverID);
            stmt.setInt(2, driverID);
            stmt.setInt(3, requestID);
            
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("[ERROR] Driver is not eligible to take the request");
                return;
            }

            int passengerID = rs.getInt("pid");
            String startLocation = rs.getString("start_location");
            String destination = rs.getString("destination");
            Calendar startTime = Calendar.getInstance();

            stmt = db.prepareStatement(
                "UPDATE requests\n" +
                "SET taken = TRUE\n" +
                "WHERE rid = ?"
            );
            stmt.setInt(1, requestID);
            stmt.executeUpdate();

            trip.takeRequest(driverID, passengerID, startTime, startLocation, destination, db);

            rs = trip.getActiveRequest(driverID, db);
            int tripID = rs.getInt("tid");
            String passengerName = rs.getString("name");

            System.out.println("Trip ID, Passenger name, Start");
            System.out.println(
                tripID + ", " + 
                passengerName + ", " + 
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    format(startTime.getTime())
            );

        } catch (SQLException e) {
            System.out.println("[ERROR] Driver take request: " + e);
            e.printStackTrace();
        }
    }

    public void finishTrip(int driverID, Scanner sc) {
        try {
            ResultSet rs = trip.getActiveRequest(driverID, db);
            int tripID = rs.getInt("tid");
            String passengerID = rs.getString("pid");
            Timestamp startTime = rs.getTimestamp("start_time");

            System.out.println("Trip ID, Passenger ID, Start");
            System.out.println(
                tripID + ", " +
                passengerID + ", " +
                startTime
            );

            System.out.println("Do you wish to finish the trip? [y/n]");
            String resp = sc.next().trim();
            if (resp.equals("y")) {
                Calendar endTime = Calendar.getInstance();
                rs = trip.finishRequest(driverID, endTime, db);

                String passengerName = rs.getString("name");
                int fee = rs.getInt("fee");

                System.out.println("Trip ID, Passenger name, Start, End, Fee");
                System.out.println(
                    tripID + ", " +
                    passengerName + ", " +
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                        format(startTime.getTime()) + ", " +
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                        format(endTime.getTime()) + ", " +
                    fee
                );
            }
            return;

        } catch (SQLException e) {
            System.out.println("[ERROR] Driver finish trip: " + e);
            e.printStackTrace();
        }
    }
    
    private boolean isVacant(int driverID) {
        try {
            PreparedStatement stmt = db.prepareStatement(
                "SELECT *\n" +
                "FROM trips\n" +
                "WHERE\n" +
                "   did = ? AND\n" + 
                "   end_time IS NULL\n" + 
                "LIMIT 1"
            );
            stmt.setInt(1, driverID);

            ResultSet rs = stmt.executeQuery();
            return !rs.next();

        } catch (SQLException e) {
            System.out.println("[ERROR] Checking vacancy: " + e);
            e.printStackTrace();
        }
        
        return false;
    }
}
