package cli;

import java.util.Scanner;

import cli.modules.FactoryRole;
import cli.modules.RoleEnum;
import cli.validators.Option;

public class MainCLI extends AbstractCLI implements CLIInterface {
    // TODO: Add DB handler inside the constructor arguments
    public MainCLI() {
        this.sc = new Scanner(System.in);
        this.greetingMsg = "Welcome! Who are you?";

        RoleEnum[] roles = RoleEnum.values();
        for (int i = 0; i < roles.length; i ++) {
            this.options.add(new Option(i + 1, roles[i].getRoleString()));
        }
    }

    public void runCLI() {
        int choice = getChoice();
        CLIInterface cli = FactoryRole.getRoleFromChoice(choice, sc);
        cli.runCLI();
    }
}
