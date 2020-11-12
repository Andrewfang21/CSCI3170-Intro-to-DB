package cli.validators;

import java.util.ArrayList;

public class IntegerValidator extends Validator {
    public IntegerValidator(UserInput data) {
        this.context = data.context;
        this.input = data.input;
    }

    @Override
    public ArrayList<String> validate() {
        boolean isPositiveInteger = true;
        try {
            int flag = Integer.parseInt(this.input);
            if (flag < 0)
                isPositiveInteger = false;
        } catch (NumberFormatException e) {
            isPositiveInteger = false;
        }

        if (!isPositiveInteger) {
            this.errorMsg.add(
                String.format("[ERROR] %s must be a positive integer.", this.context)
            );
        }
        return this.errorMsg;
    }
}
