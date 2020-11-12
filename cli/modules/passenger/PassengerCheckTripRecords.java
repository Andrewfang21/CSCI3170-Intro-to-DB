package cli.modules.passenger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.StringInput;
import cli.validators.UserInput;
import service.PassengerService;

public class PassengerCheckTripRecords extends AbstractPassenger implements CLIInterface {
    private Calendar startDate;
    private Calendar endDate;
    private String destination;

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

    public void execute() {
    }
}
