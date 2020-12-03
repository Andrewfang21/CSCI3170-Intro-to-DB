package cli.validators;

import java.util.ArrayList;

import service.PassengerService;

public class LocationValidator extends Validator<String>{
    private PassengerService service;

    public LocationValidator(UserInput<String> data, PassengerService service) {
        this.context = data.context;
        this.input = data.input;
        this.data = data;
        this.service = service;
    }

    @Override
    public ArrayList<String> validate(){
        data.errorMsg = data.validate();
        boolean isExists = service.locationExists(input);
        if (!isExists) {
            data.errorMsg.add(
                String.format("[ERROR] %s is not found", context)
            );
        }

        return data.errorMsg;
    }
}
