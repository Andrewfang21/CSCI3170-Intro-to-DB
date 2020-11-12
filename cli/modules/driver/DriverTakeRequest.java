package cli.modules.driver;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.IntegerInput;
import cli.validators.IntegerValidator;
import cli.validators.UserInput;
import service.DriverService;

public class DriverTakeRequest extends AbstractDriver implements CLIInterface {
    private int requestID;

    public DriverTakeRequest(Scanner sc, DriverService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        setDriverID();
        setRequestID();
        execute();
    }

    private void setRequestID() {
        System.out.println("Please enter the request ID.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new IntegerInput("Request ID", rawInput);
            input = new IntegerValidator(input);

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            requestID = Integer.parseInt(rawInput);
            break;
        }

    }

    @Override
    public void execute() {
        service.takeRequest(driverID, requestID);
    }
}
