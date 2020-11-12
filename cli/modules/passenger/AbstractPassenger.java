package cli.modules.passenger;

import java.util.ArrayList;
import java.util.Scanner;

import cli.validators.IntegerInput;
import cli.validators.IntegerValidator;
import cli.validators.UserInput;
import service.PassengerService;

public abstract class AbstractPassenger {
    public Scanner sc;
    public PassengerService service;
    public int passengerID;

    abstract public void execute();
    
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
}
