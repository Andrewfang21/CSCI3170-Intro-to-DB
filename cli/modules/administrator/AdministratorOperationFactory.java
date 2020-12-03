package cli.modules.administrator;

import java.util.Scanner;

import cli.CLIInterface;
import service.AdministratorService;

public class AdministratorOperationFactory {
    public static CLIInterface createOperation(int choice, Scanner sc, AdministratorService service) {
        if (choice > AdministratorEnum.values().length)
            return null;

        AdministratorEnum op = AdministratorEnum.values()[choice - 1];
        switch (op) {
            case CREATE_TABLES:
                return new AdministratorCreateTables(service);
            case DELETE_TABLES:
                return new AdministratorDeleteTables(service);
            case LOAD_DATA:
              return new AdministratorLoadData(sc, service);
            case CHECK_DATA:
                return new AdministratorCheckData(service);
            case GO_BACK:
                return null;
            default:
                return null;
        }
    }
}
