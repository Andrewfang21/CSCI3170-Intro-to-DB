package cli.validators;

import java.util.ArrayList;

public class IsGreaterValidator extends Validator {
    private String otherContext;
    private int otherValue;

    public IsGreaterValidator(UserInput data, String otherContext, int otherValue) {
        this.data = data;
        this.otherContext = otherContext;
        this.otherValue = otherValue;
    }

    @Override
    public ArrayList<String> validate() {
        boolean isGreater = Integer.parseInt(this.data.input) >= this.otherValue;
        if (!isGreater) {
            this.errorMsg.add(
              String.format("[ERROR] %s must be greater or equal than %s", this.data.context, otherContext)
            );
        }
        return this.errorMsg;
    }
}
