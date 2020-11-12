package cli.modules.manager;

import java.util.Scanner;

import cli.CLIInterface;
import cli.MainCLI;
import service.ManagerService;

public class ManagerOperationFactory {
    public static CLIInterface createOperation(int choice, Scanner sc, ManagerService service) {
        if (choice > ManagerEnum.values().length)
            return null;

        ManagerEnum op = ManagerEnum.values()[choice - 1];
        switch (op) {
            case FIND_TRIPS:
                return new ManagerFindTrips(sc, service);
            case GO_BACK:
                return new MainCLI();
            default:
                return null;
        }
    }
}
