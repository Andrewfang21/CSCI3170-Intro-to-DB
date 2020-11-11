package cli.modules.administrator;

import java.util.ArrayList;
import java.util.Scanner;

import cli.CLIInterface;
import cli.validators.Option;
import service.AdministratorService;

public class AdministratorCLI implements CLIInterface {
    private Scanner sc;
    private AdministratorService service;
    private ArrayList<Option> options = new ArrayList<Option>();
    
    public AdministratorCLI(Scanner sc, AdministratorService service) {
        this.sc = sc;
        this.service = service;

        this.options.add(new Option(1, "Create tables"));
        this.options.add(new Option(2, "Delete tables"));
        this.options.add(new Option(3, "Load data"));
        this.options.add(new Option(4, "Check data"));
        this.options.add(new Option(5, "Go back"));
    }

    @Override
    public void runCLI() {
        int choice = getChoice();
        CLIInterface a = AdministratorOperationFactory.createOperation(choice, sc, service);
        a.runCLI();
    }

    // TODO: Redundant function
    private int getChoice() {
        System.out.println("Administrator, what would you like to do?");
        for (Option o : options) {
            System.out.println(o);
        }
        sc.nextLine();

        while (true) {
            System.out.printf("Please enter [1-%d]\n", options.size());
            String choiceString = this.sc.nextLine();
            // TODO: Validate them
            String errorMsg = "";
            if (errorMsg == null) {
                return Integer.parseInt(choiceString);
            }
            System.out.println(errorMsg);            
        }
    }
}
