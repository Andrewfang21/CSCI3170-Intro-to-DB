package cli.modules.passenger;

import java.util.Scanner;

import cli.CLIInterface;
import cli.AbstractCLI;
import cli.validators.Option;
import service.PassengerService;

public class PassengerCLI extends AbstractCLI implements CLIInterface {
    private PassengerService service;

    public PassengerCLI(Scanner sc, PassengerService service) {
        this.sc = sc;
        this.service = service;
        this.greetingMsg = "Passenger, what would you like to do?";

        PassengerEnum[] op = PassengerEnum.values();
        for (int i = 0; i < op.length; i ++) {
            this.options.add(new Option(i + 1, op[i].getOperation()));
        }
    }

    public void runCLI() {
        int choice = getChoice();
        CLIInterface p = PassengerOperationFactory.createOperation(choice, sc, service);
        p.runCLI();
    }
}
