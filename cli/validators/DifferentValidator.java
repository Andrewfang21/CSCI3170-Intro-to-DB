package cli.validators;

import java.util.ArrayList;

public class DifferentValidator extends Validator {
    private String otherContext;
    private String otherValue;

    public DifferentValidator(UserInput data, String otherContext, String otherValue) {
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
