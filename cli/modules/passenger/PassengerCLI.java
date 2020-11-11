package cli.modules.passenger;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.Option;
import cli.validators.OptionValidator;
import service.PassengerService;

public class PassengerCLI implements CLIInterface {
    private Scanner sc;
    private PassengerService service;
    private ArrayList<Option> options = new ArrayList<Option>();
    private OptionValidator validator;

    public PassengerCLI(Scanner sc, PassengerService service) {
        this.sc = sc;
        this.service = service;

        this.options.add(new Option(1, "Request a ride"));
        this.options.add(new Option(2, "Check trip records"));
        this.options.add(new Option(3, "Go back"));
        this.validator = new OptionValidator(options.size());
    }

    public void runCLI() {
        int choice = getChoice();
        CLIInterface p = PassengerOperationFactory.createOperation(choice, sc, service);
        p.runCLI();
    }

    // TODO: Redundant function
    private int getChoice(){
        System.out.println("Passenger, what would you like to do?");
        for (Option o : options) {
            System.out.println(o);
        }
        sc.nextLine();

        while (true) {
            System.out.printf("Please enter [1-%d]\n", options.size());
            String choiceString = this.sc.nextLine();
            String errorMsg = this.validator.validate(choiceString);
            if (errorMsg == null) {
                return Integer.parseInt(choiceString);
            }
            System.out.println(errorMsg);
        }
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
