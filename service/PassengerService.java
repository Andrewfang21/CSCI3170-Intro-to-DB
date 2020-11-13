package service;

import java.sql.Connection;

public class PassengerService {
    private Connection db;

    public PassengerService(Connection db) {
        this.db = db;
    }

    public void requestRide(
        int ID, int passengersCount, int minDrivingYears,
        String start, String destination, String model
    ) {}

    public void checkTripRecords(int ID,
        String startDate, String endDate, String destination
    ) {}
}
