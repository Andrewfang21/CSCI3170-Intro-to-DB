package cli.validators;

import java.util.ArrayList;

public abstract class Validator extends UserInput {
    UserInput data;
    public abstract ArrayList<String> validate();
}
