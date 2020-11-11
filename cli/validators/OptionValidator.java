package cli.validators;

public class OptionValidator implements ValidatorInterface {
    private int noOfOptions;
    
    public OptionValidator(int noOfOptions) {
        this.noOfOptions = noOfOptions;
    }

    @Override
    public String validate(Object data) {
        int option;
        try {
            option = Integer.parseInt(String.valueOf(data));
        } catch (NumberFormatException e) {
            return "[ERROR] Invalid ID. ID must be a positive integer.";
        }

        if (option < 0) {
            return "[ERROR] Invalid ID. ID must be a positive integer.";
        } else if (option > noOfOptions) {
            return "[ERROR] Invalid Input.";
        }
        return null;        
    }
}
