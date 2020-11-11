package cli;

import java.util.Scanner;

public class MainCLI implements CLIInterface {
    private Scanner sc;

    // TODO: Add DB handler inside the constructor arguments
    public MainCLI() {
        this.sc = new Scanner(System.in);
    }

    public void runCLI() {
        while (true) {
            this.displayOptions();
            // TODO: Handle if the input is non-integer ?
            int choice = this.sc.nextInt();
            CLIInterface cli = FactoryRole.getRoleFromChoice(choice, sc);
            if (cli == null) {
                System.out.println("[ERROR] Invalid input.");
                continue;
            }

            cli.runCLI();
        }
    }

    private void displayOptions() {
        RoleEnum[] roles = RoleEnum.values();

        System.out.println("Welcome! Who are you?");
        for (int i = 0; i < roles.length; i ++) {
            System.out.println(i + 1 + ". " + roles[i].getRoleString());
        }
        System.out.println("Please enter [1-4]");
    }
}
