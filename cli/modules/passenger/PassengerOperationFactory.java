package cli.modules.passenger;

import java.util.Scanner;

import cli.CLIInterface;
import service.PassengerService;

public class PassengerOperationFactory {
    public static CLIInterface createOperation(int choice, Scanner sc, PassengerService service) {
        if (choice > PassengerEnum.values().length)
            return null;

        PassengerEnum op = PassengerEnum.values()[choice - 1];
        switch (op) {
            case REQUEST_A_RIDE:
                return new PassengerRequestRide(sc, service);
            case CHECK_TRIP_RECORDS:
                return new PassengerCheckTripRecords(sc, service);
            case GO_BACK:
                return null;
            default:
                return null;
        }
    }
}