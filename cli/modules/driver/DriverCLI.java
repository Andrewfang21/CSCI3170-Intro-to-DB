package cli.modules.driver;

import java.util.Scanner;

import cli.CLIInterface;
import cli.AbstractCLI;
import cli.validators.Option;
import service.DriverService;

public class DriverCLI extends AbstractCLI implements CLIInterface {
    private DriverService service;

    public DriverCLI(Scanner sc, DriverService service) {
        this.sc = sc;
        this.service = service;
        this.greetingMsg = "Driver, what would you like to do?";

        DriverEnum[] op = DriverEnum.values();
        for (int i = 0; i < op.length; i ++) {
            this.options.add(new Option(i + 1, op[i].getOperation()));
        }
    }

    @Override
    public void runCLI() {
        int choice = getChoice();
        CLIInterface d = DriverOperationFactory.createOperation(choice, sc, service);
        d.runCLI();
    }
}
