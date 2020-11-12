package cli.modules.administrator;

import java.util.Scanner;

import cli.CLIInterface;
import cli.AbstractCLI;
import cli.validators.Option;
import service.AdministratorService;

public class AdministratorCLI extends AbstractCLI implements CLIInterface {
    private AdministratorService service;
    
    public AdministratorCLI(Scanner sc, AdministratorService service) {
        this.sc = sc;
        this.service = service;
        this.greetingMsg = "Administrator, what would you like to do?";

        AdministratorEnum[] op = AdministratorEnum.values();
        for (int i = 0; i < op.length; i ++) {
            this.options.add(new Option(i + 1, op[i].getOperation()));
        }
    }

    @Override
    public void runCLI() {
        int choice = getChoice();
        CLIInterface a = AdministratorOperationFactory.createOperation(choice, sc, service);
        a.runCLI();
    }
}
