package cli.modules.driver;

import java.util.ArrayList;
import java.util.Scanner;

import cli.validators.IntegerInput;
import cli.validators.IntegerValidator;
import cli.validators.UserInput;
import service.DriverService;

public abstract class AbstractDriver {
    public Scanner sc;
    public DriverService service;
    public int driverID;

    public void setDriverID() {
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

            driverID = Integer.parseInt(rawInput);
            break;
        }
    }

    abstract public void execute();
}
