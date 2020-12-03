package cli.validators;

import java.util.ArrayList;

public class DifferentValidator<T> extends Validator<T> {
    private String otherContext;
    private T otherValue;

    public DifferentValidator(UserInput<T> data, String otherContext, T otherValue) {
        this.data = data;
        this.otherContext = otherContext;
        this.otherValue = otherValue;
    }

    @Override
    public ArrayList<String> validate() {
        data.errorMsg = data.validate();
        if (data.input.equals(otherValue)) {
            data.errorMsg.add(String.format("[ERROR] %s and %s should be different", data.context, otherContext));
        }
        return data.errorMsg;
    }
}
