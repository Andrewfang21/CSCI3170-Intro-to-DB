package cli.validators;

public class IntegerInput extends UserInput<Integer> {
    public IntegerInput(String context, int input) {
        this.context = context;
        this.input = input;
    }
}
