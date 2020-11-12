package cli.modules.passenger;

public enum PassengerEnum {
    REQUEST_A_RIDE("Request a ride"),
    CHECK_TRIP_RECORDS("Check trip records"),
    GO_BACK("Go back");

    private String operation;

    PassengerEnum(String op) {
        this.operation = op;
    }

    public String getOperation() {
        return this.operation;
    }
}
