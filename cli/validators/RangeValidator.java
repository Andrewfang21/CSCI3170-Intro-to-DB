package cli.validators;

import java.util.ArrayList;

public class RangeValidator extends Validator<Integer> {
    private int rangeLeft;
    private int rangeRight;

    public RangeValidator(UserInput<Integer> data, int left, int right) {
        this.context = data.context;
        this.input = data.input;
        this.rangeLeft = left;
        this.rangeRight = right;
    }

    @Override
    public ArrayList<String> validate() {
        int data = this.input;
        if (data < rangeLeft || data > rangeRight) {
            this.errorMsg.add(
                String.format("[ERROR] Invalid input.")
            );
        }
        return this.errorMsg;
    }
}
