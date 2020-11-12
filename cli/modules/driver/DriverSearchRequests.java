package cli.modules.driver;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.IntegerInput;
import cli.validators.IntegerValidator;
import cli.validators.UserInput;
import service.DriverService;

public class DriverSearchRequests extends AbstractDriver implements CLIInterface {
    private Scanner sc;
    private DriverService service;

    private int driverID;
    private int locationX, locationY;
    private int maxDistance;

    public DriverSearchRequests(Scanner sc, DriverService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        setDriverID();
        setLocationCoordinate();
        setMaxDistance();
        execute();
    }

    private void setLocationCoordinate() {
        System.out.println("Please enter the coordinates of your location.");
        while (true) {
            String rawInput = sc.nextLine();
            String[] coordinate = 
                rawInput
                    .trim()
                    .split("\\s+");
            if (coordinate.length != 2) {
                System.out.println("[ERROR] Coordinate should have 2 values (X and Y).");
                continue;
            }

            UserInput inputX = new IntegerInput("Coordinate X", coordinate[0]);
            inputX = new IntegerValidator(inputX);
            ArrayList<String> errorMsg = inputX.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            UserInput inputY = new IntegerInput("Coordinate Y", coordinate[0]);
            inputY = new IntegerValidator(inputY);
            errorMsg = inputY.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            locationX = Integer.parseInt(coordinate[0]);
            locationY = Integer.parseInt(coordinate[1]);
            break;
        }
    }

    private void setMaxDistance() {
        System.out.println("Please enter the maximum distance from you to the passenger.");
        while (true) {
            String rawInput = sc.nextLine();
            UserInput input = new IntegerInput("Maximum distance", rawInput);
            input = new IntegerValidator(input);

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            maxDistance = Integer.parseInt(rawInput);
            break;
        }
    }

    public void execute() {
        service.searchRequests(driverID, locationX, locationY, maxDistance);
    }
}
