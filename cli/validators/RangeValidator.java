package cli.validators;

import java.util.ArrayList;

public class RangeValidator extends Validator<Integer> {
    private int rangeLeft;
    private int rangeRight;

    public RangeValidator(UserInput<Integer> data, int left, int right) {
        this.data = data;
        this.rangeLeft = left;
        this.rangeRight = right;
    }

    @Override
    public ArrayList<String> validate() {
        data.errorMsg = data.validate();
        if (data.input < rangeLeft || data.input > rangeRight) {
            data.errorMsg.add(
                String.format("[ERROR] Invalid input.")
            );
        }
        return data.errorMsg;
    }
}
