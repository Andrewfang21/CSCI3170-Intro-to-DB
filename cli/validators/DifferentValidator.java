package cli.validators;

import java.util.ArrayList;

public class DifferentValidator extends Validator {
    private String otherContext;
    private String otherValue;

    public DifferentValidator(UserInput data, String otherContext, String otherValue) {
        this.data = data;
        this.otherContext = otherContext;
        this.otherValue = otherValue;
    }

    @Override
    public ArrayList<String> validate() {
        if (!this.data.input.equals(otherValue)) {
            this.errorMsg.add(
              String.format("[ERROR] %s and %s should be different", this.data.context, otherContext)
            );

        }
        return this.errorMsg;
    }
}
