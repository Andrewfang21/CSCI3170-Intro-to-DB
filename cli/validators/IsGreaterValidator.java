package cli.validators;

import java.util.ArrayList;

public class IsGreaterValidator extends Validator {
    private String otherContext;
    private int otherValue;

    public IsGreaterValidator(UserInput data, String otherContext, int otherValue) {
        this.context = data.context;
        this.input = data.input;
        this.otherContext = otherContext;
        this.otherValue = otherValue;
    }

    @Override
    public ArrayList<String> validate() {
        boolean isGreater = Integer.parseInt(this.input) >= this.otherValue;
        if (!isGreater) {
            this.errorMsg.add(
              String.format("[ERROR] %s must be greater or equal than %s", this.context, otherContext)
            );
        }
        return this.errorMsg;
    }
}
