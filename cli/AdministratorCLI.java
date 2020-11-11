package cli;

import java.util.Scanner;

import service.AdministratorService;

public class AdministratorCLI implements CLIInterface{
    private Scanner sc;
    private AdministratorService service;
    
    public AdministratorCLI(Scanner sc, AdministratorService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        this.displayOptions();
        int choice = this.sc.nextInt();
        switch(choice) {
            case 1:
                handleCreateTables();   break;
            case 2:
                handleDeleteTables();   break;
            case 3:
                handleLoadData();   break;
            case 4:
                handleCheckData();  break;
            case 5:
                return;
            default:
                System.out.println("[ERROR] Invalid input.");
        }
    }

    private void displayOptions() {
        System.out.println("Administrator, what would you like to do?");
        System.out.println("1. Create tables");
        System.out.println("2. Delete tables");
        System.out.println("3. Load data");
        System.out.println("4. Check data");
        System.out.println("5. Go back");
        System.out.println("Please enter [1-5]");        
    }

    private void handleCreateTables() {
        this.service.createTables();
    }
    private void handleDeleteTables() {
        this.service.deleteTables();
    }
    private void handleLoadData() {
        this.service.loadData();
    }
    private void handleCheckData() {
        this.service.checkData();
    }
}
