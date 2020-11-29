package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.ModelEnum;
import models.ModelInterface;

public class AdministratorService {
    private Connection db;

    public AdministratorService(Connection db) {
        this.db = db;
    }

    public void createTables() throws SQLException {
        PreparedStatement[] stmts = {
            db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS vehicles (\n" +
                "   vid VARCHAR(6) NOT NULL UNIQUE,\n" +
                "   model VARCHAR(30) NOT NULL,\n" +
                "   seats INTEGER NOT NULL,\n" +
                "   PRIMARY KEY (vid)\n" +
                ")"
            ),
            db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS drivers (\n" +
                "   did INTEGER NOT NULL UNIQUE,\n" +
                "   name VARCHAR(30) NOT NULL,\n" +
                "   vid VARCHAR(6) NOT NULL,\n" +
                "   driving_years INTEGER NOT NULL,\n" +
                "   PRIMARY KEY (did),\n" +
                "   FOREIGN KEY (vid) REFERENCES vehicles(vid) ON DELETE CASCADE\n" +
                ")"
            ),
            db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS passengers (\n" +
                "   pid INTEGER NOT NULL UNIQUE,\n" +
                "   name VARCHAR(30) NOT NULL\n" +
                ")"
            ),
            db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS taxi_stops (\n" +
                "   name VARCHAR(20) NOT NULL UNIQUE,\n" +
                "   loc_x INTEGER NOT NULL,\n" +
                "   loc_y INTEGER NOT NULL,\n" +
                "   PRIMARY KEY (name)\n" +
                ")"
            ),
            db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS trips (\n" +
                "   tid INTEGER AUTO_INCREMENT NOT NULL UNIQUE,\n" +
                "   did INTEGER NOT NULL,\n" +
                "   pid INTEGER NOT NULL,\n" +
                "   start_time DATETIME NOT NULL,\n" +
                "   end_time DATETIME DEFAULT NULL,\n" +
                "   start_location VARCHAR(20) NOT NULL,\n" +
                "   destination VARCHAR(20) NOT NULL,\n" +
                "   fee INTEGER DEFAULT NULL,\n" +
                "   PRIMARY KEY(tid),\n" +
                "   FOREIGN KEY (did) REFERENCES drivers(did) ON DELETE CASCADE,\n" +
                "   FOREIGN KEY (pid) REFERENCES passengers(pid) ON DELETE CASCADE,\n" +
                "   FOREIGN KEY (start_location) REFERENCES taxi_stops(name) ON DELETE CASCADE,\n" +
                "   FOREIGN KEY (destination) REFERENCES taxi_stops(name) ON DELETE CASCADE\n" +
                ")"
            ),
            db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS requests (\n" +
                "   rid INTEGER NOT NULL UNIQUE AUTO_INCREMENT,\n" +
                "   pid INTEGER NOT NULL,\n" +
                "   start_location VARCHAR(20) NOT NULL,\n" +
                "   destination VARCHAR(20) NOT NULL,\n" +
                "   model VARCHAR(30) NOT NULL,\n" + 
                "   passengers INTEGER NOT NULL, \n" +
                "   taken TINYINT(1) NOT NULL,\n" +
                "   driving_years INTEGER NOT NULL,\n" +
                "   PRIMARY KEY(rid),\n" +
                "   FOREIGN KEY (pid) REFERENCES passengers(pid) ON DELETE CASCADE,\n" +
                "   FOREIGN KEY (start_location) REFERENCES taxi_stops(name) ON DELETE CASCADE,\n" +
                "   FOREIGN KEY (destination) REFERENCES taxi_stops(name) ON DELETE CASCADE\n" +
                ")"
            ),
            
        };

        for (PreparedStatement s : stmts) {
            s.execute();
        }
    }

    public void deleteTables() throws SQLException {
        // TODO:
        // ON DELETE CASCADE in foreign key fails, check why
        for (ModelEnum m : ModelEnum.values()) {
            PreparedStatement[] stmts = {
                db.prepareStatement("SET foreign_key_checks = 0"),
                db.prepareStatement(
                    "DROP TABLE IF EXISTS " + m.getName() + " CASCADE"
                ),
                db.prepareStatement("SET foreign_key_checks = 1"),
            };

            for (PreparedStatement stmt : stmts) {
                stmt.execute();
            }
        }
    }

    public void loadData(String path) {
        for (ModelEnum m : ModelEnum.values()) {
            String filePath = path + "/" + m.getName() + ".csv";
            try {

                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line = reader.readLine();
                while (line != null) {
                    ModelInterface model = (ModelInterface)
                        m.getClassName().
                            getDeclaredConstructor().
                            newInstance();

                    model.parse(line);
                    model.insert(db);

                    line = reader.readLine();
                }
                reader.close();

            } catch (Exception e) {
                if (e.toString().contains("request"))
                    continue;

                System.out.println(e);
            }
        }
    }

    public void checkData() {
        for (ModelEnum m : ModelEnum.values()) {
            try {
                PreparedStatement stmt = db.prepareStatement(
                    "SELECT COUNT(*) FROM " + m.getName()
                );
                ResultSet rs = stmt.executeQuery();
                rs.next();

                String[] splitted = m.getName().split("_");
                for (int i = 0; i < splitted.length; i ++)
                    splitted[i] = splitted[i].substring(0, 1).toUpperCase() +
                                  splitted[i].substring(1);

                String field = String.join("_", splitted);
                int result = rs.getInt(1);

                System.out.println(field + ": " + result);

            } catch (SQLException e) {
                System.out.println("[ERROR] Error reading table: " + e);
            }
        }
    }
}
