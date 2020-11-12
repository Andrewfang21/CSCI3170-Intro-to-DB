package cli.validators;

import java.util.ArrayList;

public abstract class Validator extends UserInput {
    public abstract ArrayList<String> validate();
}
