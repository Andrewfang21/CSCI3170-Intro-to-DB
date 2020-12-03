package cli.validators;

import java.util.ArrayList;

public abstract class Validator<T> extends UserInput<T> {
    public UserInput<T> data;
    public ArrayList<String> errorMsg = new ArrayList<String>();

    public abstract ArrayList<String> validate();
}
