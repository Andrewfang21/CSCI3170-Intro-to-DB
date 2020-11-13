package cli.modules.passenger;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.DifferentValidator;
import cli.validators.IntegerInput;
import cli.validators.RangeValidator;
import cli.validators.StringInput;
import cli.validators.UserInput;
import service.PassengerService;

public class PassengerRequestRide extends AbstractPassenger implements CLIInterface {
    private int passengersCount;
    private int minDrivingYears;
    private String start;
    private String destination;
    private String model;

    final private static int MIN_PASSENGERS = 1;
    final private static int MAX_PASSENGERS = 8;

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

    private void setPassengersCount() {
        System.out.println("Please enter the number of passengers.");
        while (true) {
            int rawInput = sc.nextInt();
            UserInput<Integer> input = new IntegerInput("Number of passengers", rawInput);
            input = new RangeValidator(input, MIN_PASSENGERS, MAX_PASSENGERS);

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            passengersCount = rawInput;
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
            UserInput<String> input = new StringInput("Destination", rawInput);
            input = new DifferentValidator<String>(input, "start location", start);

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
        String rawInput = sc.nextLine();
        if (!rawInput.isBlank()) {
            minDrivingYears = Integer.parseInt(rawInput);
            return;
        }
        
        minDrivingYears = 0;
    }

    public void execute() {
        service.requestRide(passengerID, passengersCount, minDrivingYears, start, destination, model);
    }
}
