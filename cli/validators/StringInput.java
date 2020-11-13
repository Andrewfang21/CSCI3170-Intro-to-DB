package cli.validators;

public class StringInput extends UserInput<String> {
    public StringInput(String context, String input) {
        this.context = context;
        this.input = input;
    }
}
