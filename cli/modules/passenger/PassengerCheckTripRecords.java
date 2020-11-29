package cli.modules.passenger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.DifferentValidator;
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
            // TODO:
            // Check date format
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            try{
                cal.setTime(sdf.parse(rawInput));
            }catch(Exception e){
                System.out.println("[Error] Unable to parse date");
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
            // TODO:
            // Check date format
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            try{
                cal.setTime(sdf.parse(rawInput));
            }catch(Exception e){
                System.out.println("[Error] Unable to parse date");
                continue;
            }

            endDate = cal;
            break;
        }
    }

    private void setDestination() {
        // TODO:
        // Check if location is exist in the database
        System.out.println("Please enter destination.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput<String> input = new StringInput("Destination", rawInput);
            
            if(!service.locationExists(rawInput)) {
                System.out.println("[Error] Your location is not found in our database");
                continue;
            }

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
