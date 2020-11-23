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
        while (true){
            int rawInput = sc.nextInt();
            //input = new IdValidator(input);
            
            //service.checkId(rawInput);
            if (!service.checkId(rawInput)){
                System.out.println("[Error] Your ID is not found in our database");
                continue;
            }
            passengerID = rawInput;
            break;
        }
    }
}
