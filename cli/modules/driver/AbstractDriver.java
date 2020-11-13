package cli.modules.driver;

import java.util.Scanner;

import service.DriverService;

public abstract class AbstractDriver {
    public Scanner sc;
    public DriverService service;
    public int driverID;

    public void setDriverID() {
        System.out.println("Please enter your ID.");
        driverID = sc.nextInt();
    }

    abstract public void execute();
}
