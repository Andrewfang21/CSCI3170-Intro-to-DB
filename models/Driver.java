package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Driver implements ModelInterface {
    private int ID;
    private String name;
    private String vehicleID;
    private int drivingYears;

    @Override
    public void parse(String input) {
        String[] data = input.split(",");

        ID = Integer.parseInt(data[0]);
        name = data[1];
        vehicleID = data[2];
        drivingYears = Integer.parseInt(data[3]);
    }

    @Override
    public void insert(Connection conn) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO driver (did, name, vid, driving_years)\n" +
                "VALUES (?,?,?,?)"
            );
            stmt.setInt(1, ID);
            stmt.setString(2, name);
            stmt.setString(3, vehicleID);
            stmt.setInt(4, drivingYears);
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("[ERROR] Error inserting driver: " + e);
        }
    }
    
}