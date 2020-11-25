package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerService {
    private Connection db;

    public ManagerService(Connection db) {
        this.db = db;
    }

    public void findTrips(int minTravelingDistance, int maxTravelingDistance) {
        try {
            PreparedStatement stmt = db.prepareStatement(
                "SELECT\n" +
                "   tid,\n" +
                "   D.name as driver_name,\n" +
                "   P.name as passenger_name,\n" +
                "   TR.start_location, TR.destination,\n" +
                "   fee as duration\n" +
                "FROM\n" +
                "   trips as TR,\n" +
                "   drivers as D,\n" +
                "   passengers as P\n" +
                "WHERE\n" +
                "   TR.did = D.did AND\n" +
                "   TR.pid = P.pid AND\n" +
                "(\n" +
                "   SELECT COUNT(*)\n" +
                "   FROM taxi_stops A, taxi_stops B\n" +
                "   WHERE\n" +
                "       ABS(A.loc_x - B.loc_x) + ABS(A.loc_y - B.loc_y) >= ? AND\n" +
                "       ABS(A.loc_x - B.loc_x) + ABS(A.loc_y - B.loc_y) <= ? AND\n" +
                "       TR.start_location = A.name AND\n" +
                "       TR.destination = B.name\n" +
                ") > 0"
            );
            stmt.setInt(1, minTravelingDistance);
            stmt.setInt(2, maxTravelingDistance);

            ResultSet rs = stmt.executeQuery();
            
            System.out.println("trip id, deriver name, passenger name, start location, destination, duration");
            while (rs.next()) {
                System.out.printf(
                    "%s, %s, %s, %s, %s, %s\n",
                    rs.getString("tid"),
                    rs.getString("driver_name"), rs.getString("passenger_name"),
                    rs.getString("start_location"), rs.getString("destination"),
                    rs.getString("duration")
                );
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] Finding trips: " + e);
            e.printStackTrace();
        }
    }
}
