package cli;

import java.util.Scanner;

import service.DriverService;

public class DriverCLI implements CLIInterface{
    private Scanner sc;
    private DriverService service;

    public DriverCLI(Scanner sc, DriverService service) {
        this.sc = sc;
        this.service = service;
    }

    public void runCLI() {
        this.displayOptions();
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                handleSearchRequests(); break;
            case 2:
                handleTakeRequest();    break;
            case 3:
                handleFinishTrip(); break;
            case 4:
                return;
            default:
                System.out.println("[ERROR] Invalid input.");
        }
    }
    
    private void  displayOptions() {
        System.out.println("Driver, what would you like to do?");
        System.out.println("1. Search requests");
        System.out.println("2. Take a request");
        System.out.println("3. Finish a trip");
        System.out.println("4. Go back");
        System.out.println("Please enter [1-4]");
    }

    private void handleSearchRequests() {
        int driverID, locationX, locationY, maxDistance;

        System.out.println("Please enter your ID.");
        driverID = this.sc.nextInt();

        System.out.println("Please enter the coordinates of your location.");
        locationX = this.sc.nextInt();
        locationY = this.sc.nextInt();

        System.out.println("Please enter the maximum distance from you to the passenger.");
        maxDistance = this.sc.nextInt();

        this.service.searchRequests(driverID, locationX, locationY, maxDistance);
    }

    private void handleTakeRequest() {
        int driverID, requestID;
        System.out.println("Please enter your ID.");
        driverID = this.sc.nextInt();

        System.out.println("Please enter the request ID.");
        requestID = this.sc.nextInt();

        this.service.takeRequest(driverID, requestID);
    }

    private void handleFinishTrip() {
        System.out.println("Please enter your ID.");
        int driverID = this.sc.nextInt();

        this.service.finishTrip(driverID);
    }
}
