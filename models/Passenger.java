package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Passenger implements ModelInterface {
    private int ID;
    private String name;

    @Override
    public void parse(String input) {
        String[] data = input.split(",");
        assert(data.length == Passenger.class.getFields().length);

        ID = Integer.parseInt(data[0]);
        name = data[1];
    }

    @Override
    public void insert(Connection conn) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO passengers (pid, name)\n" +
                "VALUES (?,?)"
            );
            stmt.setInt(1, ID);
            stmt.setString(2, name);
            stmt.execute();

        } catch(SQLException e) {
            System.out.println("[ERROR] Error inserting passenger: " + e);
        }
    }
}
