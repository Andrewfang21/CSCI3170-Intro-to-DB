package cli.modules.manager;

import java.util.Scanner;

import cli.CLIInterface;
import cli.OperationInterface;
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
        minTravelingDistance = sc.nextInt();
    }

    private void setMaxTravelingDistance() {
        System.out.println("Please enter the maximum traveling distance.");
        maxTravelingDistance = sc.nextInt();
    }

    public void execute() {
        this.service.findTrips(minTravelingDistance, maxTravelingDistance);
    }
}
