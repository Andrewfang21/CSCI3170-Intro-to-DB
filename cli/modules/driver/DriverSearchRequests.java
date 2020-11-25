package cli.modules.driver;

import java.util.Scanner;

import cli.CLIInterface;
import service.DriverService;

public class DriverSearchRequests extends AbstractDriver implements CLIInterface {
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
        locationX = sc.nextInt();
        locationY = sc.nextInt();
    }

    private void setMaxDistance() {
        System.out.println("Please enter the maximum distance from you to the passenger.");
        maxDistance = sc.nextInt();
    }

    public void execute() {
        service.searchRequests(driverID, locationX, locationY, maxDistance);
    }
}
