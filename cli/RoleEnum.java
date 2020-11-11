package cli;

public enum RoleEnum {
    ADMINISTRATOR("An administrator"),
    PASSENGER("A passenger"),
    DRIVER("A driver"),
    MANAGER("A manager"),
    NONE("None of the above");

    private String roleString;

    RoleEnum(String roleString) {
        this.roleString = roleString;
    }

    public String getRoleString() {
        return this.roleString;
    }
}
