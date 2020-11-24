package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Vehicle implements ModelInterface {
    private String ID;
    private String model;
    private int seats;

    @Override
    public void parse(String input) {
        String[] data = input.split(",");

        ID = data[0];
        model = data[1];
        seats = Integer.parseInt(data[2]);
    }

    @Override
    public void insert(Connection conn) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO vehicle (vid, model, seats)\n" +
                "VALUES (?,?,?)"
            );
            stmt.setString(1, ID);
            stmt.setString(2, model);
            stmt.setInt(3, seats);
            stmt.execute();

        } catch(SQLException e) {
            System.out.println("[ERROR] Error inserting vehicle: " + e);
        }
    }
}
