package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                "INSERT INTO trips (\n" +
                "   tid, did, pid,\n" +
                "   start_time, end_time,\n" +
                "   start_location, destination,\n" +
                "   fee\n" +
                ") VALUES (?,?,?,?,?,?,?,?)"
            );
            stmt.setInt(1, ID);
            stmt.setInt(2, driverID);
            stmt.setInt(3, passengerID);
            stmt.setTimestamp(4, new Timestamp(startTime.getTimeInMillis()));
            stmt.setTimestamp(5, new Timestamp(endTime.getTimeInMillis()));
            stmt.setString(6, startLocation);
            stmt.setString(7, destination);
            stmt.setInt(8, fee);
            stmt.execute();

        } catch(SQLException e) {
            System.out.println("[ERROR] Error inserting trips: " + e);
        }
    }

    public void takeRequest(
        int driverID,
        int passengerID,
        Calendar startTime,
        String startLocation,
        String destination,
        Connection conn
    ) throws SQLException {

        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO trips (did, pid, start_time, start_location, destination)\n" +
            "VALUES (?,?,?,?,?)"
        );
        stmt.setInt(1, driverID);
        stmt.setInt(2, passengerID);
        stmt.setTimestamp(3, new Timestamp(startTime.getTimeInMillis()));
        stmt.setString(4, startLocation);
        stmt.setString(5, destination);
        stmt.execute();
    }

    public ResultSet finishRequest(
        int driverID,
        Calendar endTime,
        Connection conn
    ) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE trips\n" +
            "SET\n" +
            "   end_time = ?,\n" +
            "   fee = MINUTE(TIMEDIFF(?, start_time))\n" +
            "WHERE\n" +
            "   did = ? AND\n" +
            "   end_time IS NULL and fee IS NULL"
        );
        stmt.setTimestamp(1, new Timestamp(endTime.getTimeInMillis()));
        stmt.setTimestamp(2, new Timestamp(endTime.getTimeInMillis()));
        stmt.setInt(3, driverID);
        stmt.execute();

        stmt = conn.prepareStatement(
            "SELECT tid, P.name, start_time, end_time, fee\n" +
            "FROM\n" +
            "   passengers AS P, trips AS T\n" +
            "WHERE\n" +
            "   T.pid = P.pid AND\n" +
            "   T.did = ?\n" +
            "ORDER BY end_time DESC\n" +
            "LIMIT 1"
        );
        stmt.setInt(1, driverID);

        ResultSet rs = stmt.executeQuery();
        rs.next();

        return rs;
    }

    public ResultSet getActiveRequest(
        int driverID,
        Connection conn
    ) throws SQLException {

        PreparedStatement stmt = conn.prepareStatement(
            "SELECT tid, name, start_time, passengers.pid\n" +
            "FROM trips, passengers\n" +
            "WHERE\n" +
            "   did = ? AND\n" +
            "   end_time IS NULL AND fee IS NULL AND\n" +
            "   passengers.pid = trips.pid"
        );
        stmt.setInt(1, driverID);

        ResultSet rs = stmt.executeQuery();
        rs.next();

        return rs;
    }
}
