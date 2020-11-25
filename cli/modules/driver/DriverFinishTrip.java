package cli.modules.driver;

import java.util.Scanner;

import cli.CLIInterface;
import service.DriverService;

public class DriverFinishTrip extends AbstractDriver implements CLIInterface {
    
    public DriverFinishTrip(Scanner sc, DriverService service) {
        this.sc = sc;
        this.service = service;
    }

    @Override
    public void runCLI() {
        setDriverID();
        execute();
    }

    @Override
    public void execute() {
        service.finishTrip(driverID, sc);
    }
}
