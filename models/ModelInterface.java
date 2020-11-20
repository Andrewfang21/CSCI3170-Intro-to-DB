package models;

import java.sql.Connection;

public interface ModelInterface {
    public void parse(String input);
    public void insert(Connection conn);
}
