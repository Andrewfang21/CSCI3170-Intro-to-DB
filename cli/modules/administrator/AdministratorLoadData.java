package cli.modules.administrator;

import java.util.Scanner;

import cli.CLIInterface;
import service.AdministratorService;

public class AdministratorLoadData implements CLIInterface {
    private AdministratorService service;
    private Scanner sc;
    private String folderPath;

    public AdministratorLoadData(Scanner sc, AdministratorService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        setFolderPath();
        execute();
    }

    private void setFolderPath() {
        folderPath = sc.nextLine();
        // TODO:
        // Check if folderPath is valid
    }

    private void execute() {
        service.loadData(folderPath);
    }
}
