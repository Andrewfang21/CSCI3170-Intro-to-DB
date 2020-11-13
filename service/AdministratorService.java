package service;

import java.sql.Connection;

public class AdministratorService {
    private Connection db;
    
    public AdministratorService(Connection db) {
        this.db = db;
    }

    public void createTables() {}
    public void deleteTables() {}
    public void loadData(String path) {}
    public void checkData() {}
}
