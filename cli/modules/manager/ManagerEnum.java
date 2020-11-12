package cli.modules.manager;

public enum ManagerEnum {
    FIND_TRIPS("Find trips"),
    GO_BACK("Go back");

    private String operation;

    ManagerEnum(String op) {
        this.operation = op;
    }

    public String getOperation() {
        return this.operation;
    }
}
