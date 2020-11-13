package cli.modules.driver;

import java.util.Scanner;

import cli.CLIInterface;
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
        requestID = sc.nextInt();
    }

    @Override
    public void execute() {
        service.takeRequest(driverID, requestID);
    }
}
