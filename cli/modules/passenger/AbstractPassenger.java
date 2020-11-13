package cli.modules.passenger;

import java.util.Scanner;

import service.PassengerService;

public abstract class AbstractPassenger {
    public Scanner sc;
    public PassengerService service;
    public int passengerID;

    abstract public void execute();
    
    public void setPassengerID() {
        System.out.println("Please enter your ID.");
        passengerID = sc.nextInt();
    }
}
