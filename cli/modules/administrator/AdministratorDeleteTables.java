package cli.modules.administrator;

import java.sql.SQLException;

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
        try {

            System.out.print("Processing...");
            this.service.deleteTables();
            System.out.println("Done! Tables are deleted");

        } catch (SQLException e) {
            System.out.print("[ERROR] Failed to drop tables: " + e);
        }
    }
}
