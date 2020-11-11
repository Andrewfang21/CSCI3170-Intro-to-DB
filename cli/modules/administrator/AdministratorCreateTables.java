package cli.modules.administrator;

import cli.CLIInterface;
import service.AdministratorService;

public class AdministratorCreateTables implements CLIInterface {
    private AdministratorService service;

    public AdministratorCreateTables(AdministratorService service) {
        this.service = service;
    }

    @Override
    public void runCLI() {
        execute();
    }

    private void execute() {
        this.service.createTables();
    }
}
