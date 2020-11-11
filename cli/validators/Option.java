package cli.validators;

public class Option {
    private int number;
    private String description;

    public Option(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public String toString() {
        return number + ". " + description;
    }
}
