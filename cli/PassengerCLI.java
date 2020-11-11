package cli;

import java.util.Scanner;

import service.PassengerService;

public class PassengerCLI implements CLIInterface {
    private Scanner sc;
    private PassengerService service;

    public PassengerCLI(Scanner sc, PassengerService service) {
        this.sc = sc;
        this.service = service;
    }

    public void runCLI() {
        this.displayOptions();
        int choice = this.sc.nextInt();
        switch(choice) {
            case 1:
                handleRideRequest();    break;
            case 2:
                handleCheckTripRecords();   break;
            case 3:
                return;
            default:
                System.out.println("[ERROR] Invalid input.");
        }
    }

    private void displayOptions() {
        System.out.println("Passenger, what would you like to do?");
        System.out.println("1. Request a ride");
        System.out.println("2. Check trip records");
        System.out.println("3. Go back");
        System.out.println("Please enter [1-3]");        
    }

    private void handleRideRequest() {
        int ID, passengersCount, minDrivingYears;
        String start, destination, model;

        System.out.println("Please enter your ID.");
        ID = this.sc.nextInt();

        System.out.println("Please enter the number of passengers.");
        passengersCount = this.sc.nextInt();
        
        System.out.println("Please enter the start location.");
        start = this.sc.nextLine();
        
        System.out.println("Please enter the destination.");
        destination = this.sc.nextLine();
        
        System.out.println("Please enter the model. (Press enter to skip)");
        model = this.sc.nextLine();
        
        System.out.println("Please enter the minimum driving years of the driver. (Press enter to skip)");
        minDrivingYears = this.sc.nextInt();

        this.service.requestRide(ID, passengersCount, minDrivingYears, start, destination, model);
    }

    private void handleCheckTripRecords() {
        int ID;
        String start, end, destination;

        System.out.println("Please enter your ID.");
        ID = this.sc.nextInt();

        System.out.println("Please enter the start date.");
        start = this.sc.nextLine();

        System.out.println("Please enter the end date.");
        end = this.sc.nextLine();

        System.out.println("Please enter the destination.");
        destination = this.sc.nextLine();

        // TODO:
        // Handle date parsing

        this.service.checkTripRecords(ID, start, end, destination);
    }
}
