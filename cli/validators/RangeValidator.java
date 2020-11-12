package cli.validators;

import java.util.ArrayList;

public class RangeValidator extends Validator {
    private int rangeLeft;
    private int rangeRight;

    public RangeValidator(UserInput data, int left, int right) {
        this.context = data.context;
        this.input = data.input;
        this.rangeLeft = left;
        this.rangeRight = right;
    }

    @Override
    public ArrayList<String> validate() {
        int data = Integer.parseInt(this.input);
        if (data < rangeLeft || data > rangeRight) {
            this.errorMsg.add(
                String.format("[ERROR] %s must be in between %d and %d (inclusive).", this.context, rangeLeft, rangeRight)
            );
        }
        return this.errorMsg;
    }
}
