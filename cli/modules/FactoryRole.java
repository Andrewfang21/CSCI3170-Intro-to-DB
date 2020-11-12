package cli.modules;

import java.util.Scanner;

import cli.CLIInterface;
import cli.modules.administrator.AdministratorCLI;
import cli.modules.manager.ManagerCLI;
import cli.modules.passenger.PassengerCLI;
import cli.modules.driver.DriverCLI;
import service.AdministratorService;
import service.DriverService;
import service.ManagerService;
import service.PassengerService;

public class FactoryRole {
    public static CLIInterface getRoleFromChoice(int choice, Scanner sc) {
        if (choice > RoleEnum.values().length)
            return null;

        RoleEnum role = RoleEnum.values()[choice - 1];
        switch(role) {
            case ADMINISTRATOR:
                return new AdministratorCLI(sc, new AdministratorService());
            case PASSENGER:
                return new PassengerCLI(sc, new PassengerService());
            case DRIVER:
                return new DriverCLI(sc, new DriverService());
            case MANAGER:
                return new ManagerCLI(sc, new ManagerService());
            default:
                System.exit(0);
        }

        return null;
    }
}
