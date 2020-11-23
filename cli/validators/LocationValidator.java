package cli.validators;

import java.util.ArrayList;

public class LocationValidator extends Validator<String>{
    public LocationValidator(UserInput<String> data){
        this.context = data.context;
        this.input = data.input;
    }

    @Override
    public ArrayList<String> validate(){
        String data = this.input;

        this.errorMsg.add(
            String.format("[ERROR] Invalid input.")
        );
        return this.errorMsg;
    }
}
