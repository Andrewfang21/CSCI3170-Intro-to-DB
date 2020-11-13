package cli.validators;

import java.util.ArrayList;

public abstract class UserInput<T> {
    String context;
    T input;
    ArrayList<String> errorMsg = new ArrayList<String>();

    public ArrayList<String> validate() {
        return this.errorMsg;
    }
}
