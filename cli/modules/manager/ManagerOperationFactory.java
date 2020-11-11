package cli.modules.manager;

import java.util.Scanner;

import cli.CLIInterface;
import cli.MainCLI;
import service.ManagerService;

public class ManagerOperationFactory {
    public static CLIInterface createOperation(int choice, Scanner sc, ManagerService service) {
        if (choice == 1) {
            return new ManagerFindTrips(sc, service);
        }
        return new MainCLI();
    }
}
