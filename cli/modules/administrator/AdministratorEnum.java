package cli.modules.administrator;

public enum AdministratorEnum {
    CREATE_TABLES("Create tables"),
    DELETE_TABLES("Delete tables"),
    LOAD_DATA("Load data"),
    CHECK_DATA("Check data"),
    GO_BACK("Go back");

    private String operation;

    AdministratorEnum(String op) {
        this.operation = op;
    }

    public String getOperation() {
        return this.operation;
    }
}
