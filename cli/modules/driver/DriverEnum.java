package cli.modules.driver;

public enum DriverEnum {
    SEARCH_REQUESTS("Search requests"),
    TAKE_A_REQUEST("Take a request"),
    FINISH_A_TRIP("Finish a trip"),
    GO_BACK("Go back");

    private String operation;

    DriverEnum(String op) {
        this.operation = op;
    }

    public String getOperation() {
        return this.operation;
    }
}
