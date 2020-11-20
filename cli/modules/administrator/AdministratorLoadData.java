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
    }

    private void setFolderPath() {
        System.out.println("Please enter the folder path");
        folderPath = sc.next().trim();
        try {

            System.out.print("Processing...");
            execute();
            System.out.println("Data is loaded!");

        } catch (Exception e) {
            System.out.println("[ERROR] Cannot load data from file: " + e);
        }
    }

    private void execute() {
        service.loadData(folderPath);
    }
}
