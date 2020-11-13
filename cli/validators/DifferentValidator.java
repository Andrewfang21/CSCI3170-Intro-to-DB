package cli.validators;

import java.util.ArrayList;

public class DifferentValidator<T> extends Validator<T> {
    private String otherContext;
    private T otherValue;

    public DifferentValidator(UserInput<T> data, String otherContext, T otherValue) {
        this.context = data.context;
        this.input = data.input;
        this.otherContext = otherContext;
        this.otherValue = otherValue;
    }

    @Override
    public ArrayList<String> validate() {
        if (!this.input.equals(otherValue)) {
            this.errorMsg.add(
              String.format("[ERROR] %s and %s should be different", this.context, otherContext)
            );

        }
        return this.errorMsg;
    }
}
