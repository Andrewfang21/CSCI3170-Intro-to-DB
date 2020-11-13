package cli;

import java.util.ArrayList;
import java.util.Scanner;

import cli.validators.IntegerInput;
import cli.validators.Option;
import cli.validators.RangeValidator;
import cli.validators.UserInput;

public abstract class AbstractCLI {
    public Scanner sc;
    public ArrayList<Option> options = new ArrayList<Option>();
    public String greetingMsg;

    public int getChoice() {
        System.out.println(greetingMsg);
        for (Option o : options) {
            System.out.println(o);
        }

        System.out.printf("Please enter [1-%d]\n", options.size());
        while (true) {
            int rawInput = sc.nextInt();
            UserInput<Integer> input = new IntegerInput("Input", rawInput);
            input = new RangeValidator(input, 1, options.size());

            ArrayList<String> errorMsg = input.validate();
            if (!errorMsg.isEmpty()) {
                System.out.println(errorMsg.get(0));
                continue;
            }

            return rawInput;
        }
    }
}
