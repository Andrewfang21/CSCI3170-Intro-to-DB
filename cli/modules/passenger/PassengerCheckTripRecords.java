package cli.modules.passenger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.LocationValidator;
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
        System.out.println("Please enter the start date.");
        while (true) {
            String rawInput = sc.nextLine();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                cal.setTime(sdf.parse(rawInput));
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to parse date");
                continue;
            }

            startDate = cal;
            break;
        }
    }

    public void setEndDate() {
        System.out.println("Please enter the end date.");
        while (true) {
            String rawInput = sc.nextLine();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                cal.setTime(sdf.parse(rawInput));
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to parse date");
                continue;
            }

            endDate = cal;
            break;
        }
    }

    private void setDestination() {
        System.out.println("Please enter destination.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput<String> input = new StringInput("Destination", rawInput);
            input = new LocationValidator(input, service);

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
        service.checkTripRecords(passengerID, startDate, endDate, destination);
    }
}
