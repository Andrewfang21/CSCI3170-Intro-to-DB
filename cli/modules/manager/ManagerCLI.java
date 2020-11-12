package cli.modules.manager;

import java.util.Scanner;

import cli.CLIInterface;
import cli.AbstractCLI;
import cli.validators.Option;
import service.ManagerService;

public class ManagerCLI extends AbstractCLI implements CLIInterface {
    private ManagerService service;
    
    public ManagerCLI(Scanner sc, ManagerService service) {
        this.sc = sc;
        this.service = service;
        this.greetingMsg = "Manager, what would you like to do?";

        ManagerEnum[] op = ManagerEnum.values();
        for (int i = 0; i < op.length; i ++) {
            this.options.add(new Option(i + 1, op[i].getOperation()));
        }
    }

    public void runCLI() {
        int choice = getChoice();
        CLIInterface m = ManagerOperationFactory.createOperation(choice, sc, service);
        m.runCLI();
    }
}
