package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaxiStop implements ModelInterface {
    private String name;
    private int locationX;
    private int locationY;

    @Override
    public void parse(String input) {
        String[] data = input.split(",");

        name = data[0];
        locationX = Integer.parseInt(data[1]);
        locationY = Integer.parseInt(data[2]);
    }

    @Override
    public void insert(Connection conn) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO taxi_stops (name, loc_x, loc_y)\n" +
                "VALUES (?,?,?)"
            );
            stmt.setString(1, name);
            stmt.setInt(2, locationX);
            stmt.setInt(3, locationY);
            stmt.execute();

        } catch(SQLException e) {
            System.out.println("[ERROR] Error inserting taxi stop: " + e);
        }
    }
}
