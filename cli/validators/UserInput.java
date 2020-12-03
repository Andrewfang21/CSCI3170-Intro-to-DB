package cli.validators;

import java.util.ArrayList;

public abstract class UserInput<T> {
    public String context;
    public T input;
    public ArrayList<String> errorMsg = new ArrayList<String>();

    public ArrayList<String> validate() {
        return this.errorMsg;
    }
}
