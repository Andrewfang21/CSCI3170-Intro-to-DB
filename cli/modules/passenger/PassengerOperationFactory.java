package cli.modules.passenger;

import java.util.Scanner;

import cli.CLIInterface;
import cli.MainCLI;
import service.PassengerService;

public class PassengerOperationFactory {
    public static CLIInterface createOperation(int choice, Scanner sc, PassengerService service) {
        if (choice == 1) {
            return new PassengerRequestRide(sc, service);
        } else if (choice == 2) {
            return new PassengerCheckTripRecords(sc, service);
        }
        return new MainCLI();
    }
}