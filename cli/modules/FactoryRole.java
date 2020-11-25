package cli.modules;

import java.sql.Connection;
import java.util.Scanner;

import cli.CLIInterface;
import cli.modules.administrator.AdministratorCLI;
import cli.modules.manager.ManagerCLI;
import cli.modules.passenger.PassengerCLI;
import models.Trip;
import cli.modules.driver.DriverCLI;
import service.AdministratorService;
import service.DriverService;
import service.ManagerService;
import service.PassengerService;

public class FactoryRole {
    public static CLIInterface getRoleFromChoice(int choice, Scanner sc, Connection db) {
        if (choice > RoleEnum.values().length)
            return null;

        RoleEnum role = RoleEnum.values()[choice - 1];
        switch(role) {
            case ADMINISTRATOR:
                return new AdministratorCLI(sc, new AdministratorService(db));
            case PASSENGER:
                return new PassengerCLI(sc, new PassengerService(db));
            case DRIVER:
                return new DriverCLI(sc, new DriverService(db, new Trip()));
            case MANAGER:
                return new ManagerCLI(sc, new ManagerService(db));
            default:
                System.exit(0);
        }

        return null;
    }
}
