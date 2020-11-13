package service;

import java.sql.Connection;

public class ManagerService {
    private Connection db;

    public ManagerService(Connection db) {
        this.db = db;
    }

    public void findTrips(int minTravelingDistance, int maxTravelingDistance) {}
}
