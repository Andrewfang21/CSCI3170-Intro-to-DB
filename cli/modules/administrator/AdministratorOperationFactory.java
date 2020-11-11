package cli.modules.administrator;

import java.util.Scanner;

import cli.CLIInterface;
import cli.MainCLI;
import service.AdministratorService;

public class AdministratorOperationFactory {
    public static CLIInterface createOperation(int choice, Scanner sc, AdministratorService service) {
        if (choice == 1) {
            return new AdministratorCreateTables(service);
        } else if (choice == 2) {
            return new AdministratorDeleteTables(service);
        } else if (choice == 3) {
            return new AdministratorLoadData(sc, service);
        } else if (choice == 4) {
            return new AdministratorCheckData(service);
        }
        return new MainCLI();
    }
}
