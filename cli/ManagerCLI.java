package cli;

import java.util.Scanner;

import service.ManagerService;

public class ManagerCLI implements CLIInterface {
    private Scanner sc;
    private ManagerService service;
    
    public ManagerCLI(Scanner sc, ManagerService service) {
        this.sc = sc;
        this.service = service;
    }

    public void runCLI() {
        this.displayOptions();
        int choice = this.sc.nextInt();
        switch(choice) {
            case 1:
                handleFindTrips();  break;
            case 2:
                return;
            default:
                System.out.println("[ERROR] Invalid input.");
        }
    }

    private void displayOptions() {
        System.out.println("Manager, what would you like to do?");
        System.out.println("1. Find trips");
        System.out.println("2. Go back");
        System.out.println("Please enter [1-2]");        
    }

    private void handleFindTrips() {
        int minTravelingDistance, maxTravelingDistance;
        
        System.out.println("Please enter the minimum traveling distance.");
        minTravelingDistance = this.sc.nextInt();

        System.out.println("Please enter the maximum traveling distance.");
        maxTravelingDistance = this.sc.nextInt();

        this.service.findTrips(minTravelingDistance, maxTravelingDistance);
    }
}
