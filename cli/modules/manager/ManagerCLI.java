package cli.modules.manager;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.Option;
import cli.validators.OptionValidator;
import service.ManagerService;

public class ManagerCLI implements CLIInterface {
    private Scanner sc;
    private ManagerService service;
    private ArrayList<Option> options = new ArrayList<Option>();
    private OptionValidator validator;
    
    public ManagerCLI(Scanner sc, ManagerService service) {
        this.sc = sc;
        this.service = service;

        this.options.add(new Option(1, "Find trips"));
        this.options.add(new Option(2, "Go back"));
        this.validator = new OptionValidator(options.size());
    }

    public void runCLI() {
        int choice = getChoice();
        CLIInterface m = ManagerOperationFactory.createOperation(choice, sc, service);
        m.runCLI();
    }

    // TODO: Redundant function
    private int getChoice(){
        System.out.println("Manager, what would you like to do?");
        for (Option o : options) {
            System.out.println(o);
        }
        sc.nextLine();

        while (true) {
            System.out.printf("Please enter [1-%d]\n", options.size());
            String choiceString = this.sc.nextLine();
            String errorMsg = this.validator.validate(choiceString);
            if (errorMsg == null) {
                return Integer.parseInt(choiceString);
            }
            System.out.println(errorMsg);
        }
    }
}
