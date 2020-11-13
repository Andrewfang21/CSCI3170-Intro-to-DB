package cli.validators;

import java.util.ArrayList;

public abstract class Validator<T> extends UserInput<T> {
    public abstract ArrayList<String> validate();
}
