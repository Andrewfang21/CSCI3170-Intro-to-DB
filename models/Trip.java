package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Trip implements ModelInterface {
    private int ID;
    private int driverID;
    private int passengerID;
    private Calendar startTime;
    private Calendar endTime;
    private String startLocation;
    private String destination;
    private int fee;

    @Override
    public void parse(String input) {
        String[] data = input.split(",");

        ID = Integer.parseInt(data[0]);
        driverID = Integer.parseInt(data[1]);
        passengerID = Integer.parseInt(data[2]);
        try {
            startTime = Calendar.getInstance();
            startTime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[3]));

            endTime = Calendar.getInstance();
            endTime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[4]));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        startLocation = data[5];
        destination = data[6];
        fee = Integer.parseInt(data[7]);
    }

    @Override
    public void insert(Connection conn) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO trip (\n" +
                "   tid, did, pid,\n" +
                "   start_time, end_time,\n" +
                "   start_location, destination,\n" +
                "   fee\n" +
                ") VALUES (?,?,?,?,?,?,?,?)"
            );
            stmt.setInt(1, ID);
            stmt.setInt(2, driverID);
            stmt.setInt(3, passengerID);
            stmt.setDate(4, new Date(startTime.getTimeInMillis()));
            stmt.setDate(5, new Date(endTime.getTimeInMillis()));
            stmt.setString(6, startLocation);
            stmt.setString(7, destination);
            stmt.setInt(8, fee);
            stmt.execute();

        } catch(SQLException e) {
            System.out.println("[ERROR] Error inserting passenger: " + e);
        }

    }
}
