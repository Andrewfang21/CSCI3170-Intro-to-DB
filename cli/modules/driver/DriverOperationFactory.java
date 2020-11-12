package cli.modules.driver;

import java.util.Scanner;

import cli.CLIInterface;
import cli.MainCLI;
import service.DriverService;

public class DriverOperationFactory {
    public static CLIInterface createOperation(int choice, Scanner sc, DriverService service) {
        if (choice > DriverEnum.values().length)
            return null;
    
        DriverEnum op = DriverEnum.values()[choice - 1];
        switch (op) {
            case SEARCH_REQUESTS:
                return new DriverSearchRequests(sc, service);
            case TAKE_A_REQUEST:
                return new DriverTakeRequest(sc, service);
            case FINISH_A_TRIP:
                return new DriverFinishTrip(sc, service);
            case GO_BACK:
                return new MainCLI();
            default:
                return null;
        }
    }
}
