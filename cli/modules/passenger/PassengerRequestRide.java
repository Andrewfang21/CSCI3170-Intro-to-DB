package cli.modules.passenger;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.DifferentValidator;
import cli.validators.IntegerInput;
import cli.validators.IntegerValidator;
import cli.validators.RangeValidator;
import cli.validators.StringInput;
import cli.validators.UserInput;
import service.PassengerService;

public class PassengerRequestRide implements CLIInterface {
    private Scanner sc;
    private PassengerService service;

    private int passengerID;
    private int passengersCount;
    private int minDrivingYears;
    private String start;
    private String destination;
    private String model;

    public PassengerRequestRide(Scanner sc, PassengerService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        setPassengerID();
        setPassengersCount();
        setStartLocation();
        setDestination();
        setModel();
        setMinDrivingYears();
        execute();
    }

    private void setPassengerID() {
        System.out.println("Please enter your ID.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new IntegerInput("ID", rawInput);
            input = new IntegerValidator(input);

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            passengerID = Integer.parseInt(rawInput);
            break;
        }
    }

    private void setPassengersCount() {
        System.out.println("Please enter the number of passengers.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new IntegerInput("Number of passegners", rawInput);
            input = new RangeValidator(input, 1, 8);

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            passengersCount = Integer.parseInt(rawInput);
            break;
        }
    }

    private void setStartLocation() {
        System.out.println("Please enter the start location.");
        String input = sc.nextLine();
        // TODO:
        // Check if location is exist in the database
        start = input;
    }

    private void setDestination() {
        // TODO:
        // Check if location is exist in the database
        System.out.println("Please enter destination.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new StringInput("Destination", rawInput);
            input = new DifferentValidator(input, "start location", start);

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            destination = rawInput;
            break;
        }
    }

    private void setModel() {
        System.out.println("Please enter the model. (Please enter to skip).");
        model = sc.nextLine();
    }

    private void setMinDrivingYears() {
        System.out.println("Please enter the minimum driving years of the driver (Please enter to skip).");
        while (true) {
            String rawInput = sc.nextLine();
            if (!rawInput.isBlank()) {
                UserInput input = new IntegerInput("Minimum driving years", rawInput);
                input = new IntegerValidator(input);
    
                ArrayList<String> errorMsg = input.validate();
                if (!errorMsg.isEmpty()) {
                    System.out.println(errorMsg.get(0));
                    continue;
                }
    
                destination = rawInput;
                break;
            }
            break;
        }
    }

    public void execute() {
        
    }
}
