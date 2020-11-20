package cli.modules.administrator;

import java.sql.SQLException;

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
        try {

            System.out.print("Processing...");
            service.createTables();
            System.out.println("Done! Tables are created!");

        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to create tables: " + e);
        }
    }
}
