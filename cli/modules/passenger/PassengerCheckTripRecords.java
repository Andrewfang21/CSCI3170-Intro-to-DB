package cli.modules.passenger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.IntegerInput;
import cli.validators.IntegerValidator;
import cli.validators.StringInput;
import cli.validators.UserInput;
import service.PassengerService;

public class PassengerCheckTripRecords implements CLIInterface {
    private Scanner sc;
    private PassengerService service;

    private Calendar startDate;
    private Calendar endDate;
    private String destination;
    private int passengerID;

    public PassengerCheckTripRecords(Scanner sc, PassengerService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        setPassengerID();
        setStartDate();
        setEndDate();
        setDestination();
        execute();
    }

    public void setPassengerID() {
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

    public void setStartDate() {
        while (true) {
            String rawInput = sc.nextLine();
            // TODO:
            // Check date format
        }
    }

    public void setEndDate() {
        while (true) {
            String rawInput = sc.nextLine();
            // TODO:
            // Check date format
        }
    }

    private void setDestination() {
        // TODO:
        // Check if location is exist in the database
        System.out.println("Please enter destination.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new StringInput("Destination", rawInput);

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            destination = rawInput;
            break;
        }
    }

    private void execute() {

    }
}
