package cli.modules.manager;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.OperationInterface;
import cli.validators.IntegerInput;
import cli.validators.IntegerValidator;
import cli.validators.IsGreaterValidator;
import cli.validators.UserInput;
import service.ManagerService;

public class ManagerFindTrips implements CLIInterface, OperationInterface {
    private int minTravelingDistance;
    private int maxTravelingDistance;
    private ManagerService service;
    private Scanner sc;

    public ManagerFindTrips(Scanner sc, ManagerService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        setMinTravelingDistance();
        setMaxTravelingDistance();
        execute();
    }

    private void setMinTravelingDistance() {
        System.out.println("Please enter the minimum traveling distance.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new IntegerInput("Minimum traveling distance", rawInput);
            input = new IntegerValidator(input);
            
            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            minTravelingDistance = Integer.parseInt(rawInput);
            break;
        }
    }

    private void setMaxTravelingDistance() {
        System.out.println("Please enter the maximum traveling distance.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new IntegerInput("Maximum traveling distance", rawInput);
            input = new IntegerValidator(input);
            input = new IsGreaterValidator(input, "Minimum traveling distance", minTravelingDistance);
        
            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            maxTravelingDistance = Integer.parseInt(rawInput);
            break;
        }
    }

    public void execute() {
        this.service.findTrips(minTravelingDistance, maxTravelingDistance);
    }
}
