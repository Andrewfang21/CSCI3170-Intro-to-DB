package service;

import java.sql.Connection;

public class DriverService {
    private Connection db;

    public DriverService(Connection db) {
        this.db = db;
    }

    public void searchRequests(
        int driverID, int locationX, int locationY, int maxDistance
    ) {
    }

    public void takeRequest(int driverID, int requestID) {}

    public void finishTrip(int driverID) {}
}
