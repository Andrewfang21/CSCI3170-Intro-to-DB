package cli;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import cli.modules.FactoryRole;
import cli.modules.RoleEnum;
import cli.validators.Option;
import service.DatabaseService;

public class MainCLI extends AbstractCLI implements CLIInterface {

    public MainCLI() {
        this.sc = new Scanner(System.in);
        this.greetingMsg = "Welcome! Who are you?";

        RoleEnum[] roles = RoleEnum.values();
        for (int i = 0; i < roles.length; i ++) {
            this.options.add(new Option(i + 1, roles[i].getRoleString()));
        }
    }

    public void runCLI() {
        Connection db;
        try {
            db = DatabaseService.getConnection();
          
            int choice = getChoice();
            CLIInterface cli = FactoryRole.getRoleFromChoice(choice, sc, db);
            cli.runCLI();
          
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            
        }
    }
}
