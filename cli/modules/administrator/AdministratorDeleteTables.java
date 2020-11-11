package cli.modules.administrator;

import java.util.Scanner;

import cli.CLIInterface;
import service.AdministratorService;

public class AdministratorDeleteTables implements CLIInterface {
    private AdministratorService service;

    public AdministratorDeleteTables(AdministratorService service) {
        this.service = service;
    }

    @Override
    public void runCLI() {
        execute();
    }

    private void execute() {
        this.service.deleteTables();
    }

}
